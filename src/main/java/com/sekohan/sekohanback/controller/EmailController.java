package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.email.EmailCodeCheckDto;
import com.sekohan.sekohanback.dto.email.EmailMessageDto;
import com.sekohan.sekohanback.dto.email.EmailPostDto;
import com.sekohan.sekohanback.dto.email.EmailResponseDto;
import com.sekohan.sekohanback.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/send-mail")
@RestController
@RequiredArgsConstructor  //생성자 주입을 위한 필요한 생성자를 자동으로 생성(EmailService)을 주입받음
@Slf4j
public class EmailController {

    private final EmailService emailService;


    // 회원가입 이메일 인증 - 요청 시 body로 인증번호 반환하도록 작성하였음
    @PostMapping("/email")
    public ResponseEntity sendJoinMail(@RequestBody EmailPostDto emailPostDto) { //클라이언트로부터 전달된 EmilPostDto객체를 요청의 본문에서 읽어옴
        EmailMessageDto emailMessage = EmailMessageDto.builder()  //객체 생성, 수신자 이메일 주소와 이메일 제목을 설정
                .to(emailPostDto.getEmail())  //수신자 이메일 주소
                .subject("이메일 인증을 위한 인증 코드 발송")
                .build();

        //sendMail메서드를 호출하여 이메일을 보내고, 그 결과로 전송된 인증코드를 code에 저장
        String code = emailService.sendMail(emailMessage, "email");

        //EmailResponseDto 객체를 생성하고, 이메일로 전송된 인증 코드를 이 객체에 설정합니다.
        EmailResponseDto emailResponseDto = new EmailResponseDto();
        emailResponseDto.setCode(code);
        return ResponseEntity.ok(emailResponseDto); //객체와 함께 200 OK 상태 코드를 클라이언트에 반환
    }

    @PostMapping("/check")
    public ResponseEntity checkCode(@RequestBody EmailCodeCheckDto emailCodeCheckDto) {
        String userInput = emailCodeCheckDto.getCode();  //사용자가 입력한 코드를 emailCheckDto의 code속성을 통해 받음

        if (emailService.checkCode(userInput)) {  //인증번호 일치여부 확인
            return ResponseEntity.ok("인증성공");
        } else {
            return ResponseEntity.ok("인증실패");

        }
    }
}
