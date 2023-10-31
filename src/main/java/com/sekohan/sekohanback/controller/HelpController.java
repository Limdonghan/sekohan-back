package com.sekohan.sekohanback.controller;


import com.sekohan.sekohanback.dto.email.EmailCodeCheckDTO;
import com.sekohan.sekohanback.dto.email.EmailMessageDTO;
import com.sekohan.sekohanback.dto.email.EmailResponseDTO;
import com.sekohan.sekohanback.dto.user.help.UserIdHelpDTO;
import com.sekohan.sekohanback.dto.user.change.UserPwChangeDTO;
import com.sekohan.sekohanback.dto.user.help.UserPwHelpDTO;
import com.sekohan.sekohanback.dto.user.valid.ValidCheckDTO;
import com.sekohan.sekohanback.service.email.EmailService;
import com.sekohan.sekohanback.service.help.userHelpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/help")
@RequiredArgsConstructor
@Slf4j
public class HelpController {

    private final userHelpService userHelpService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/id")
    public ResponseEntity findID(@RequestBody UserIdHelpDTO userIdHelpDTO){
        String login = userHelpService.findLogin(userIdHelpDTO);
        return ResponseEntity.ok("아이디 찾기 : " + login);

    }

    @GetMapping("/pw")
    public ResponseEntity findPW(@RequestBody UserPwHelpDTO userPwHelpDTO){
        String password = userHelpService.findPassword(userPwHelpDTO);
        return ResponseEntity.ok("패스워드 찾기 : "+password);
    }

    @PatchMapping("/pwchange")
    public ResponseEntity pwChange(@RequestBody UserPwChangeDTO userPwChangeDTO){
        String password1 = userPwChangeDTO.getPassword1();
        String encode = passwordEncoder.encode(userPwChangeDTO.getPassword1());
        return ResponseEntity.ok("변경된비밀번호 : "+password1+"변경된 비밀번호 인코더 : "+encode);
    }

    @PostMapping("/emailcheck")
    public ResponseEntity validEmailCheck(@RequestBody ValidCheckDTO validCheckDTO){
        log.info("이메일 유효성 체크");
        boolean emailValid = userHelpService.validEmail(validCheckDTO);
        if(emailValid){  //true 이메일이 존재하면 인증번호 전송
            EmailMessageDTO emailMessageDTO = EmailMessageDTO.builder()
                    .to(validCheckDTO.getEmail())
                    .subject("이메일 인증을 위한 인증코드 발송")
                    .build();
            String code = emailService.sendMail(emailMessageDTO,"email");
            EmailResponseDTO emailResponseDTO = new EmailResponseDTO();
            emailResponseDTO.setCode(code);
            log.info("인증번호 : {}",code);
            return ResponseEntity.ok("인증번호를 전송했습니다. : "+code);
        }else{  //false
            throw new RuntimeException("존재하지 않은 이메일입니다.");
        }
    }

    @PostMapping("/codecheck")
    public ResponseEntity checkCode(@RequestBody EmailCodeCheckDTO emailCodeCheckDto) {
        String userInput = emailCodeCheckDto.getCode();  //사용자가 입력한 코드를 emailCheckDto의 code속성을 통해 받음

        if (emailService.checkCode(userInput)) {  //인증번호 일치여부 확인
            return ResponseEntity.ok("인증성공");
        } else {
            return ResponseEntity.ok("인증실패");

        }
    }


}
