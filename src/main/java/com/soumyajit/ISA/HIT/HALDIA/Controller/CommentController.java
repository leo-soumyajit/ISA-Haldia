package com.soumyajit.ISA.HIT.HALDIA.Controller;

import com.soumyajit.ISA.HIT.HALDIA.Dtos.CommentDtos;
import com.soumyajit.ISA.HIT.HALDIA.Dtos.CommentRequestDtos;
import com.soumyajit.ISA.HIT.HALDIA.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/{postId}")
    public ResponseEntity<CommentDtos> addPost(@PathVariable Long postId ,
                                               @RequestBody CommentRequestDtos commentRequestDtos){
        return ResponseEntity.ok(commentService.addComment(commentRequestDtos,postId));
    }

    @DeleteMapping("/{commentId}/post/{postId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long postId ,
                                              @PathVariable Long commentId){
        commentService.deleteComment(postId,commentId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{commentId}/post/{postId}")
    public ResponseEntity<CommentDtos> updateComment(@PathVariable Long postId ,
                                               @RequestBody CommentRequestDtos commentRequestDtos,
                                                     @PathVariable Long commentId ){
        return ResponseEntity.ok(commentService.updateComment(commentRequestDtos,postId,commentId));
    }

}
