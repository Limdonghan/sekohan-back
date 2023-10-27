package com.sekohan.sekohanback.dto.email;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmailMessageDTO {  //메일 전송 DTO
    
    private String to;  //수신자 메일주소
    private String subject;  //메일 제목
    private String message;  //메일 내용
}
