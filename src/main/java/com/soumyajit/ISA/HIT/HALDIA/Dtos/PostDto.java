package com.soumyajit.ISA.HIT.HALDIA.Dtos;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.OrderColumn;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Data
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private List<String> imgOrVdos;
    private String userName;
    private Long userId;
    private Long likes;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    //private LocalDateTime createdAt;
    private List<CommentDtos> comments;
}