package com.expasoft.ExpaWebForum.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity(name = "tb_post")
@Getter
@Setter
@RequiredArgsConstructor
public class PostEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    private String title;
    private String content;
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private UserEntity user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private Set<CommentEntity> comment;

}
