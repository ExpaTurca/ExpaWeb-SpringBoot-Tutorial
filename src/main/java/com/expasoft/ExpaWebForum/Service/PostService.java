package com.expasoft.ExpaWebForum.Service;

import com.expasoft.ExpaWebForum.Entity.DTO.PostDTO;
import com.expasoft.ExpaWebForum.Entity.PostEntity;
import com.expasoft.ExpaWebForum.Repository.PostRepository;
import com.expasoft.ExpaWebForum.Service.CRUD.ICrud;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService implements ICrud<PostDTO> {

    PostRepository rep;

    @Override
    public ResponseEntity<?> getOne(UUID id) {
        return ResponseEntity.of(rep.findById(id));
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> save(PostDTO postDTO) {
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> update(UUID id, PostDTO postDTO) {
        return ResponseEntity.noContent().build();
    }

    @Override
    public int delete(UUID id) {
        return -1;
    }
}
