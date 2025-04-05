package com.soumyajit.ISA.HIT.HALDIA.Dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class CommentDtos {
    private Long id;
    private String content;
    //private LocalDateTime createdAt;
    private String userName;
    private Long userId;
    private Long postId;
}
