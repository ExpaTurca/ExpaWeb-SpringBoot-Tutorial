package com.expasoft.ExpaWebForum.Controller;

import com.expasoft.ExpaWebForum.Entity.DTO.PostDTO;
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

    @GetMapping("?post={post_id}&owner={owner_id}")
    private ResponseEntity<?> getOnePost(
            @RequestParam("post_id") UUID post_id,
            @RequestParam("owner_id") UUID owner_id) {
        return postService.getOne(post_id, owner_id);
    }

    @GetMapping("all")
    private ResponseEntity<?> getAll() {
        return postService.getAll();
    }

    @PostMapping("new")
    private ResponseEntity<?> savePost(@RequestBody NewPostForm newPostForm) {
        return postService.save(
                new NewPostForm()
        );
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
