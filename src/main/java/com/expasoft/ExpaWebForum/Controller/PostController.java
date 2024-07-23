package com.expasoft.ExpaWebForum.Controller;

import com.expasoft.ExpaWebForum.Entity.DTO.PostDTO;
import com.expasoft.ExpaWebForum.Entity.Template.NewPostForm;
import com.expasoft.ExpaWebForum.Entity.Template.UpdatePostForm;
import com.expasoft.ExpaWebForum.Service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/v3/post")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("{post_id}")
    private Optional<PostDTO> getOnePost(@PathVariable("post_id") UUID post_id) {
        return postService.getOne(post_id);
    }

    @GetMapping("all")
    private Set<PostDTO> getAllPost() {
        return postService.getAll();
    }


    //FIXME: Yeni paylaşım yapılırken kullanıcı eklenmiyor. Düzelt.
    @PostMapping("new")
    private Optional<PostDTO> savePost(@RequestBody NewPostForm newPostForm) {
        return postService.save(newPostForm);
    }

    @PatchMapping("{post_id}/update")
    private Optional<PostDTO> updatePost(@PathVariable("post_id") UUID post_id, @RequestBody UpdatePostForm updatePostForm) {
        return postService.update(
                        post_id,
                        updatePostForm);
    }

    @DeleteMapping("{post_id}/delete")
    private int deletePost(@PathVariable("post_id") UUID post_id) {
        return postService.delete(
                post_id
        );
    }
}
