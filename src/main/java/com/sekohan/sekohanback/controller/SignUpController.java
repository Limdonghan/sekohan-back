package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.ValidCheckDTO;
import com.sekohan.sekohanback.dto.UserSignUpDTO;
import com.sekohan.sekohanback.service.signUp.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor //생성자를 자동으로 생성해주는 어노테이션
@RequestMapping("/api/user")
public class SignUpController {

    private final SignUpService signUpService;

    private final PasswordEncoder passwordEncoder;


    //회원가입
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)  //회원가입메서드 이니깐 status = 201
    public ResponseEntity signup(@RequestBody UserSignUpDTO userSignUpDTO){
        String encode = passwordEncoder.encode(userSignUpDTO.getPassword());
        userSignUpDTO.setPassword(encode);
        signUpService.signUp(userSignUpDTO);
        return ResponseEntity.ok("회원가입성공");
    }

    //사용자 ID 유효성 체크
    @PostMapping("/idcheck")
    public ResponseEntity validIdCheck(@RequestBody ValidCheckDTO userValidCheck){
        boolean idValid = signUpService.validID(userValidCheck);
        if(idValid){
            throw new RuntimeException("이미 사용중인 아이디 입니다.");
        }else {
            return ResponseEntity.ok("사용가능한 아이디입니다.");
        }
    }

    //사용자 닉네임 유효성 체크
    @PostMapping("/nicknamecheck")
    public ResponseEntity validNicknameCheck(@RequestBody ValidCheckDTO validCheckDTO){
        boolean nicknameValid = signUpService.validNickName(validCheckDTO);
        if(nicknameValid){
            throw new RuntimeException("이미 사용중인 닉네임 입니다.");
        }else {
            return ResponseEntity.ok("사용가능한 닉네임입니다.");
        }
    }
}
