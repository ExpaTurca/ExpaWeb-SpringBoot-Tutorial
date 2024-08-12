package com.expasoft.ExpaWebForum.Service;

import com.expasoft.ExpaWebForum.Entity.DTO.PostDTO;
import com.expasoft.ExpaWebForum.Entity.DTO.UserDTO;
import com.expasoft.ExpaWebForum.Entity.PostEntity;
import com.expasoft.ExpaWebForum.Entity.Template.NewPostForm;
import com.expasoft.ExpaWebForum.Entity.Template.UpdatePostForm;
import com.expasoft.ExpaWebForum.Entity.UserEntity;
import com.expasoft.ExpaWebForum.Repository.PostRepository;
import com.expasoft.ExpaWebForum.Repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PostService {

    private final EntityManager entityManager;
    private final ModelMapper mapper;
    private final PostRepository rep;

    public Optional<PostDTO> getOne(UUID post_id) {
        return Optional.of(
                mapper.map(
                        entityManager.find(
                                PostEntity.class, post_id), PostDTO.class));
    }

    public Optional<UserDTO> getUserByPostId(UUID post_id) {
        return Optional.of(
                mapper.map(
                        rep.findUserByPostId(post_id), UserDTO.class));
    }

    public Set<PostDTO> getAll() {
        return Stream.of(entityManager.find(PostEntity.class, "*"))
                .map(res -> mapper.map(res, PostDTO.class))
                .collect(Collectors.toSet());
    }

    // TODO: Proje tamamlandıktan sonra OwnerId HttpHeader ile taşınacak.
    //  NewPostForm içerisinde bulunan OwnerId silinecek.
    @Transactional
    public Optional<PostDTO> save(NewPostForm newPostForm) {
        UserEntity userEntity = entityManager.find(UserEntity.class, newPostForm.getOwnerId());

        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(newPostForm.getTitle());
        postEntity.setContent(newPostForm.getContent());
        postEntity.setUser(userEntity);

        entityManager.refresh(userEntity);
        entityManager.persist(postEntity);

        return Optional.of(postEntity).
                map(m -> mapper.map(m, PostDTO.class));
    }

    public Optional<PostDTO> update(UUID post_id, UpdatePostForm updatePostForm) {

        PostEntity post = entityManager.find(PostEntity.class, post_id);
        post.setTitle(updatePostForm.getTitle());
        post.setContent(updatePostForm.getContent());

        entityManager.persist(post);
        return Optional.of(post).
                map(m -> mapper.map(m, PostDTO.class));
    }

    public int delete(UUID id) {
        try {
            entityManager.detach(entityManager.find(PostEntity.class, id));
            return 1;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return -1;
        }
    }
}
