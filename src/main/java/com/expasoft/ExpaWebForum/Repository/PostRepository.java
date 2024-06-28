package com.expasoft.ExpaWebForum.Repository;

import com.expasoft.ExpaWebForum.Entity.PostEntity;
import com.expasoft.ExpaWebForum.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {

    @Query(value = "SELECT p, u.id FROM Post p INNER JOIN Post.user u WHERE p.id=:post_id", nativeQuery = true)
    Optional<UserEntity> findUserByPostId(@Param("post_id") UUID post_id);
}
