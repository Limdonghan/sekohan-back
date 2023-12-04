package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.user.sign.UserSignUpDTO;
import com.sekohan.sekohanback.dto.user.valid.ValidCheckDTO;
import com.sekohan.sekohanback.service.user.signup.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor //생성자를 자동으로 생성해주는 어노테이션
@RequestMapping("/api/user")
public class SignUpController {

    private final SignUpService signUpService;
    private final PasswordEncoder passwordEncoder;
    
    // RequestDTO <- @Validated(DTO에서 유효성검사) : 유지보수에 용이함

    //회원가입
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)  //회원가입메서드 이니깐 status = 201
    public ResponseEntity signup(@Validated UserSignUpDTO userSignUpDTO){
        String encode = passwordEncoder.encode(userSignUpDTO.getPassword());
        userSignUpDTO.setPassword(encode);
        signUpService.signUp(userSignUpDTO);
        return ResponseEntity.ok("회원가입성공");
    }

    //사용자 ID 유효성 체크
    @PostMapping("/idcheck")
    public ResponseEntity validIdCheck(@Validated @RequestBody ValidCheckDTO validCheckDTO){
        boolean idValid = signUpService.validID(validCheckDTO);
        if(idValid){  //true
            throw new RuntimeException("이미 사용중인 아이디 입니다.");
        }else {  //false
            return ResponseEntity.ok("사용가능한 아이디입니다.");
        }
    }

    /* 사용자 닉네임 유효성 체크 */
    @PostMapping("/nicknamecheck")
    public ResponseEntity validNicknameCheck(@Validated @RequestBody ValidCheckDTO validCheckDTO){
        boolean nicknameValid = signUpService.validNickName(validCheckDTO);
        if(nicknameValid){  //true
            throw new RuntimeException("이미 사용중인 닉네임 입니다.");
        }else {  //false
            return ResponseEntity.ok("사용가능한 닉네임입니다.");
        }
    }




}
