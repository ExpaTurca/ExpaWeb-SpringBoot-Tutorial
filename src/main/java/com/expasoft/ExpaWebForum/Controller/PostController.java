package com.expasoft.ExpaWebForum.Controller;

import com.expasoft.ExpaWebForum.Entity.DTO.PostDTO;
import com.expasoft.ExpaWebForum.Entity.Template.NewPostForm;
import com.expasoft.ExpaWebForum.Entity.Template.UpdatePostForm;
import com.expasoft.ExpaWebForum.Service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v3/post")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("{post_id}")
    private ResponseEntity<?> getOnePost(@PathVariable("post_id") UUID post_id) {
        System.out.println(post_id);
        return ResponseEntity.of(
                postService.getOne(post_id));
    }

    @GetMapping("all")
    private Optional<PostDTO> getAllPost() {
        return postService.getAll();
    }

    @PostMapping("new")
    private ResponseEntity<?> savePost(@RequestBody NewPostForm newPostForm) {
        return ResponseEntity.ofNullable(
                postService.save(
                        newPostForm));
    }

    @PatchMapping("{post_id}/update")
    private ResponseEntity<?> updatePost(@PathVariable("post_id") UUID post_id, @RequestBody UpdatePostForm updatePostForm) {
        return ResponseEntity.ofNullable(
                postService.update(
                        post_id,
                        updatePostForm));
    }

    @DeleteMapping("{post_id}/delete")
    private int deletePost(@PathVariable("post_id") UUID post_id) {
        return postService.delete(
                post_id
        );
    }
}
