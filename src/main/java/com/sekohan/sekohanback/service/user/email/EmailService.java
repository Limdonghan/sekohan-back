package com.sekohan.sekohanback.service.user.email;

import com.sekohan.sekohanback.dto.email.EmailMessageDTO;


public interface EmailService {

    //메일을 보내는 메서드
    String sendMail(EmailMessageDTO emailMessageDto, String type);

    //사용자로부터 받은 인증번호 체크 메서드
    boolean checkCode(String userInput);
}
