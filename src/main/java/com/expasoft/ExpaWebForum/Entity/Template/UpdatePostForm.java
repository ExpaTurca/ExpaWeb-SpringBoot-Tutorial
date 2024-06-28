package com.expasoft.ExpaWebForum.Entity.Template;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdatePostForm {
    private String title;
    private String content;
}
