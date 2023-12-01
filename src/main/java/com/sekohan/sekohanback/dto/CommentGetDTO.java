package com.sekohan.sekohanback.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CommentGetDTO {
    private Long commentId;
    private Long UID;
    private String username;
    private String content;
    private String localDateTime;
}
