package com.expasoft.ExpaWebForum.Service;

import com.expasoft.ExpaWebForum.Entity.CommentEntity;
import com.expasoft.ExpaWebForum.Entity.DTO.CommentDTO;
import com.expasoft.ExpaWebForum.Entity.DTO.UserDTO;
import com.expasoft.ExpaWebForum.Entity.PostEntity;
import com.expasoft.ExpaWebForum.Entity.Template.NewCommentForm;
import com.expasoft.ExpaWebForum.Entity.Template.UpdateCommentForm;
import com.expasoft.ExpaWebForum.Entity.UserEntity;
import com.expasoft.ExpaWebForum.Repository.CommentRepository;
import com.expasoft.ExpaWebForum.Repository.PostRepository;
import com.expasoft.ExpaWebForum.Repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import javax.xml.stream.events.Comment;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService{

    private final ModelMapper mapper;
    private final EntityManager entityManager;
    private final CommentRepository rep;

    public Optional<CommentDTO> getOne(UUID id) {
        return Optional.of(
                entityManager.find(
                        CommentEntity.class, id)).map(m -> mapper.map(m, CommentDTO.class));
    }

    public Set<CommentDTO> getAll() {
        return rep.findAll().stream().map(res -> mapper.map(res, CommentDTO.class)).collect(Collectors.toSet());
    }

    @Transactional
    public Optional<CommentDTO> save(NewCommentForm newCommentForm) {
        UserEntity user = entityManager.find(UserEntity.class, newCommentForm.getOwnerId());
        PostEntity post = entityManager.find(PostEntity.class, newCommentForm.getPostId());

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setUser(user);
        commentEntity.setPost(post);
        commentEntity.setContent(newCommentForm.getContent());

        entityManager.refresh(entityManager.merge(user));
        entityManager.refresh(entityManager.merge(post));
        entityManager.persist(commentEntity);

        return Optional.of(
                commentEntity).map(m -> mapper.map(m, CommentDTO.class));
    }

    @Transactional
    public Optional<CommentDTO> update(UUID id, UpdateCommentForm updateCommentForm) {
        CommentEntity commentEntity = entityManager.find(CommentEntity.class, id);
        commentEntity.setContent(updateCommentForm.getContent());
        entityManager.persist(commentEntity);
        return Optional.of(
                commentEntity).map(m -> mapper.map(m, CommentDTO.class));
    }


    public int delete(UUID id) {
        return -1;
    }
}
