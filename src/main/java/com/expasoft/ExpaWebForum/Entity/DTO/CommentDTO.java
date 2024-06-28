package com.expasoft.ExpaWebForum.Entity.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
public class CommentDTO {

    private String content;
    private Date createdAt;
    private PostDTO postDTO;
    private UserDTO userDTO;

}
