package com.expasoft.ExpaWebForum.Repository;

import com.expasoft.ExpaWebForum.Entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {

}
