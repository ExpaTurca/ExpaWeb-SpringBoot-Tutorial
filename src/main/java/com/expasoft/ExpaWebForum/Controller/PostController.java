package com.expasoft.ExpaWebForum.Controller;

import com.expasoft.ExpaWebForum.Entity.DTO.PostDTO;
import com.expasoft.ExpaWebForum.Entity.PostEntity;
import com.expasoft.ExpaWebForum.Entity.Template.NewPostForm;
import com.expasoft.ExpaWebForum.Entity.Template.UpdatePostForm;
import com.expasoft.ExpaWebForum.Service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v3/post/")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("{id}")
    private ResponseEntity<?> getPost(@RequestParam UUID id) {
        return postService.getOne(id);
    }

    @GetMapping("all")
    private ResponseEntity<?> getAll() {
        return postService.getAll();
    }

    @PostMapping("new")
    private ResponseEntity<?> savePost(@RequestBody NewPostForm newPostForm) {
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle(newPostForm.getTitle());
        postDTO.setContent(newPostForm.getContent());
        return postService.save(postDTO);
    }

    @PatchMapping("update/{id}")
    private ResponseEntity<?> updatePost(@RequestPart UUID id, @RequestBody UpdatePostForm updatePostForm) {
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle(updatePostForm.getTitle());
        postDTO.setContent(updatePostForm.getContent());
        return postService.update(id, postDTO);
    }

    @DeleteMapping("delete/{id}")
    private int deletePost(@RequestPart UUID id) {
        return postService.delete(id);
    }
}
