package com.expasoft.ExpaWebForum.Repository;

import com.expasoft.ExpaWebForum.Entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {

}
