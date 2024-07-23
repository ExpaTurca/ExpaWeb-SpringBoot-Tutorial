package com.expasoft.ExpaWebForum.Entity.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO {

    private String username;
    private String email;
    private Date registeredAt;
}
