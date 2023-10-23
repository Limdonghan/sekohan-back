package com.sekohan.sekohanback.service;

import com.sekohan.sekohanback.dto.email.EmailMessageDto;


public interface EmailService {

    //메일을 보내는 메서드
    String sendMail(EmailMessageDto emailMessageDto, String type);

    //사용자로부터 받은 인증번호 체크 메서드
    boolean checkCode(String userInput);
}
