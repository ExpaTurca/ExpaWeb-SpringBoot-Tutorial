package com.expasoft.ExpaWebForum.Entity.Template;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RegisterForm {
    private String username;
    private String email;
    private String password;
}
