package com.expasoft.ExpaWebForum.Repository;

import com.expasoft.ExpaWebForum.Entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {

    @Query("SELECT p FROM Post p WHERE p.id=?1 and p.owner_id=?2")
    Optional<PostEntity> findOneByIdAndOwnerId(UUID post_id, UUID owner_id);
}
