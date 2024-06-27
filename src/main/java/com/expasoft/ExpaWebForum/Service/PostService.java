package com.expasoft.ExpaWebForum.Service;

import com.expasoft.ExpaWebForum.Entity.DTO.PostDTO;
import com.expasoft.ExpaWebForum.Entity.PostEntity;
import com.expasoft.ExpaWebForum.Entity.Template.NewPostForm;
import com.expasoft.ExpaWebForum.Repository.PostRepository;
import com.expasoft.ExpaWebForum.Service.CRUD.ICrud;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    PostRepository rep;

    public ResponseEntity<?> getOne(UUID post_id, UUID owner_id) {
        return ResponseEntity.ofNullable(
                rep.findById(post_id)
        );
    }

    public ResponseEntity<?> getAll() {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> save(NewPostForm newPostForm) {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(newPostForm.getTitle());
        postEntity.setContent(newPostForm.getContent());

        return ResponseEntity.ofNullable(
                rep.save(postEntity)
        );
    }

    public ResponseEntity<?> update(UUID id, PostDTO postDTO) {
        return ResponseEntity.noContent().build();
    }

    public int delete(UUID id) {
        return -1;
    }
}
