package com.sekohan.sekohanback.controller;


import com.sekohan.sekohanback.dto.email.EmailCodeCheckDTO;
import com.sekohan.sekohanback.dto.email.EmailMessageDTO;
import com.sekohan.sekohanback.dto.email.EmailResponseDTO;
import com.sekohan.sekohanback.dto.user.change.UserPwChangeDTO;
import com.sekohan.sekohanback.dto.user.help.UserIdHelpDTO;
import com.sekohan.sekohanback.dto.user.help.UserPwHelpDTO;
import com.sekohan.sekohanback.dto.user.valid.ValidCheckDTO;
import com.sekohan.sekohanback.service.user.email.EmailService;
import com.sekohan.sekohanback.service.user.help.UserHelpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/help")
@RequiredArgsConstructor
@Slf4j
public class HelpController {

    private final UserHelpService userHelpService;
    private final EmailService emailService;

    /* 유저 아이디 찾기 컨트롤러 */
    @PostMapping("/id")
    public ResponseEntity findID(@RequestBody @Validated UserIdHelpDTO userIdHelpDTO){
        String login = userHelpService.findLogin(userIdHelpDTO);
        return ResponseEntity.ok(userIdHelpDTO.getName()+"님의 아이디 : " + login);

    }

    /* 유저 비밀번호 찾기전 유저 확인 컨트롤러 */
    @PostMapping("/pw")
    public ResponseEntity findPW(@RequestBody UserPwHelpDTO userPwHelpDTO){
        userHelpService.findPassword(userPwHelpDTO);
        return ResponseEntity.ok("비밀번호 변경하러가기");
    }

    /* 유저 비밀번호 변경 컨트롤러 */
    @PatchMapping("/pwChange/{login}")   //비밀번호 변경
    public ResponseEntity pwChange(@Validated @RequestBody UserPwChangeDTO userPwChangeDTO,
                                   @PathVariable String login){
        userHelpService.passwordChange(userPwChangeDTO,login);
        return ResponseEntity.ok("Password Change Success");
    }

    /* 유저 확인 컨트롤러 */
    @PostMapping("/emailCheck")
    public ResponseEntity validEmailCheck(@RequestBody @Validated ValidCheckDTO validCheckDTO){
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

    /* 이메일 인증번호 확인 컨트롤러 */
    @PostMapping("/codeCheck")
    public ResponseEntity checkCode(@RequestBody @Validated EmailCodeCheckDTO emailCodeCheckDto) {
        String userInput = emailCodeCheckDto.getCode();  //사용자가 입력한 코드를 emailCheckDto의 code속성을 통해 받음

        if (emailService.checkCode(userInput)) {  //인증번호 일치여부 확인
            return ResponseEntity.ok("인증성공");
        } else {
            return ResponseEntity.ok("인증실패");

        }
    }


}
