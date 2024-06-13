package com.expasoft.ExpaWebForum.Service;

import com.expasoft.ExpaWebForum.Entity.DTO.CommentDTO;
import com.expasoft.ExpaWebForum.Repository.CommentRepository;
import com.expasoft.ExpaWebForum.Service.CRUD.ICrud;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService implements ICrud<CommentDTO> {

    private final CommentRepository rep;

    @Override
    public ResponseEntity<?> getOne(UUID id) {
        return ResponseEntity.of(rep.findById(id));
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> save(CommentDTO commentDTO) {
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> update(UUID id, CommentDTO commentDTO) {
        return ResponseEntity.noContent().build();
    }

    @Override
    public int delete(UUID id) {
        return -1;
    }
}
