package com.expasoft.ExpaWebForum.Repository;

import com.expasoft.ExpaWebForum.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,UUID> {

    public Optional<UserEntity> findByUsername(String username);
}
