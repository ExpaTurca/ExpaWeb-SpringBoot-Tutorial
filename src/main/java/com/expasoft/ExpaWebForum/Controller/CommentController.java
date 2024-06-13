package com.expasoft.ExpaWebForum.Controller;

import com.expasoft.ExpaWebForum.Entity.DTO.CommentDTO;
import com.expasoft.ExpaWebForum.Entity.Template.NewCommentForm;
import com.expasoft.ExpaWebForum.Entity.Template.UpdateCommentForm;
import com.expasoft.ExpaWebForum.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v3/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("{id}")
    private ResponseEntity<?> getOne(@RequestParam UUID id) {
        return commentService.getOne(id);
    }

    @GetMapping("all")
    private ResponseEntity<?> getAll(){
        return commentService.getAll();
    }

    @PostMapping("new")
    private ResponseEntity<?> saveComment(@RequestBody NewCommentForm newCommentForm){
        CommentDTO commentDTO=new CommentDTO();
        commentDTO.setContent(newCommentForm.getContent());
        return commentService.save(commentDTO);
    }

    @PatchMapping("update/{id}")
    private ResponseEntity<?> updateComment(@RequestParam UUID id, @RequestBody UpdateCommentForm updateCommentForm) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setContent(updateCommentForm.getContent());
        return commentService.update(id, commentDTO);
    }

    @DeleteMapping("delete/{id}")
    private int deleteComment(@RequestParam UUID id){
        return commentService.delete(id);
    }
}
