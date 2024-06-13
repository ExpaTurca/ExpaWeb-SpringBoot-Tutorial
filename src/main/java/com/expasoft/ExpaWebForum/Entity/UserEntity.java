package com.expasoft.ExpaWebForum.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.*;

@Entity(name = "tb_user")
@Getter
@Setter
@RequiredArgsConstructor
public class UserEntity
{

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;
    private String username;
    private String email;
    private String password;
    private Date registeredAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<PostEntity> post;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<CommentEntity> comment;
}
