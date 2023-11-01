package com.sekohan.sekohanback.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentGetDTO {
    private Long commentId;
    private Long UID;
    private String username;
    private String content;
    private LocalDateTime localDateTime;
}
