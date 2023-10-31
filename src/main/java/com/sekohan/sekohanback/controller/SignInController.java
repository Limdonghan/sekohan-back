package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.user.sign.UserSignInDTO;
import com.sekohan.sekohanback.dto.jwt.JsonWebTokenResponseDTO;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.security.repository.UserSecurityRepository;
import com.sekohan.sekohanback.service.Authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class SignInController {

    private final UserSecurityRepository userSecurityRepository;
    private final AuthenticationService authenticationService;

    @PostMapping("/signin")
    public JsonWebTokenResponseDTO auth(@Validated @RequestBody UserSignInDTO authRequest) {
        log.info("--------SigninController--------");

        return authenticationService.auth(authRequest);
    }

    @GetMapping("/member")
    public void exMember(){
        log.info("멤버인증중:");
        UserEntity user = userSecurityRepository.getUser();
        log.info("exMember.......... : {}",user);
    }

}
