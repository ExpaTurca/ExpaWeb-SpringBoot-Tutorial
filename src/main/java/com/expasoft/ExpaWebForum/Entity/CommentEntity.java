package com.expasoft.ExpaWebForum.Entity;

import com.expasoft.ExpaWebForum.Entity.DTO.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity(name = "tb_comment")
@Getter
@Setter
@RequiredArgsConstructor
public class CommentEntity {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;
    private String content;

    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = PostEntity.class)
    @JoinColumn(name = "postId")
    private PostEntity post;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
    @JoinColumn(name = "userId")
    private UserEntity user;
}
