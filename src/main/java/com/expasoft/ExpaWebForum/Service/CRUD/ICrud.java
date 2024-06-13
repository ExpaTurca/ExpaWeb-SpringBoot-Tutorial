package com.expasoft.ExpaWebForum.Service.CRUD;

import com.expasoft.ExpaWebForum.Entity.PostEntity;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface ICrud <T> {

    public ResponseEntity<?> getOne(UUID id);
    public ResponseEntity<?> getAll();
    public ResponseEntity<?> save(T entity);
    public ResponseEntity<?> update(UUID id, T entity);
    public int delete(UUID id);
}
