package com.sekohan.sekohanback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentGetDTO {
    private Long commentId;
    private Long UID;
    private String username;
    private String content;
    private String localDateTime;
}
