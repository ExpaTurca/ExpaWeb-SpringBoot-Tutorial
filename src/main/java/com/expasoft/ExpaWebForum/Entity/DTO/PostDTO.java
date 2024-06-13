package com.expasoft.ExpaWebForum.Entity.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
public class PostDTO {

    private String title;
    private String content;
    private Date createdAt;
    private UserDTO user;
}
