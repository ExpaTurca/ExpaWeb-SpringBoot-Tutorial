package com.expasoft.ExpaWebForum.Service;

import com.expasoft.ExpaWebForum.Entity.DTO.PostDTO;
import com.expasoft.ExpaWebForum.Entity.DTO.UserDTO;
import com.expasoft.ExpaWebForum.Entity.PostEntity;
import com.expasoft.ExpaWebForum.Entity.Template.NewPostForm;
import com.expasoft.ExpaWebForum.Entity.Template.UpdatePostForm;
import com.expasoft.ExpaWebForum.Entity.UserEntity;
import com.expasoft.ExpaWebForum.Repository.PostRepository;
import com.expasoft.ExpaWebForum.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserService userService;
    private final ModelMapper mapper;
    private final UserRepository userRep;
    private final PostRepository rep;

    public Optional<PostDTO> getOne(UUID post_id) {
        return Optional.ofNullable(
                mapper.map(
                        rep.findById(post_id),
                        PostDTO.class));
    }

    public Optional<UserDTO> getUserByPostId(UUID post_id) {
        return Optional.ofNullable(
                mapper.map(
                        rep.findUserByPostId(
                                post_id),
                        UserDTO.class));
    }

    public Optional<PostDTO> getAll() {
        return
                Optional.of(
                        mapper.map(
                                rep.findAll(),
                                PostDTO.class));
    }


    // TODO: Proje tamamlandıktan sonra OwnerId HttpHeader ile taşınacaktır.
    //  NewPostForm içerisinde bulunan OwnerId silinecektir.
    public Optional<PostEntity> save(NewPostForm newPostForm) {

        UserEntity userEntity = mapper.map(
                userService.getOne(newPostForm.getOwnerId()).orElseThrow(),
                UserEntity.class);

        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(newPostForm.getTitle());
        postEntity.setContent(newPostForm.getContent());
        Set<PostEntity> postEntityList = new HashSet<>();

        userEntity.setPosts(postEntityList);

        return Optional.of(
                rep.save(postEntity));
    }

    public Optional<PostEntity> update(UUID post_id, UpdatePostForm updatePostForm) {
        PostDTO postDTO = getOne(post_id).orElseThrow();
        postDTO.setTitle(updatePostForm.getTitle());
        postDTO.setContent(updatePostForm.getContent());
        return Optional.of(
                rep.save(
                        mapper.map(
                                postDTO,
                                PostEntity.class)));
    }

    public int delete(UUID id) {
        try {
            rep.deleteById(id);
            return 1;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return -1;
        }
    }
}
