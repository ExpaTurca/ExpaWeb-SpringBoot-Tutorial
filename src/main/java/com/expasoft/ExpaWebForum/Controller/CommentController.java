package com.expasoft.ExpaWebForum.Controller;

import com.expasoft.ExpaWebForum.Entity.CommentEntity;
import com.expasoft.ExpaWebForum.Entity.DTO.CommentDTO;
import com.expasoft.ExpaWebForum.Entity.Template.NewCommentForm;
import com.expasoft.ExpaWebForum.Entity.Template.UpdateCommentForm;
import com.expasoft.ExpaWebForum.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v3/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("{id}")
    private Optional<CommentDTO> getOne(@RequestParam UUID id) {
        return commentService.getOne(id);
    }

    @GetMapping("all")
    private Set<CommentDTO> getAll(){
        return commentService.getAll();
    }

    @PostMapping("new")
    private Optional<CommentDTO> saveComment(@RequestBody NewCommentForm newCommentForm) {
        return commentService.save(newCommentForm);
    }

    @PatchMapping("update/{id}")
    private Optional<CommentDTO> updateComment(@RequestParam UUID id, @RequestBody UpdateCommentForm updateCommentForm) {
        return commentService.update(id, updateCommentForm);
    }

    @DeleteMapping("delete/{id}")
    private int deleteComment(@RequestParam UUID id){
        return commentService.delete(id);
    }
}
