package com.soumyajit.ISA.HIT.HALDIA.Service;


import com.soumyajit.ISA.HIT.HALDIA.Dtos.CommentDtos;
import com.soumyajit.ISA.HIT.HALDIA.Dtos.CommentRequestDtos;

public interface CommentService {


    CommentDtos addComment(CommentRequestDtos commentRequestDtos , Long postId);
    void deleteComment(Long postId,Long commentId);
    CommentDtos updateComment(CommentRequestDtos commentRequestDtos , Long postId,Long commentId);

}
