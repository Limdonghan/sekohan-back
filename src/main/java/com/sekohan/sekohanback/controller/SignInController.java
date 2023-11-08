package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.jwt.JsonWebTokenResponseDTO;
import com.sekohan.sekohanback.dto.jwt.RefreshTokenRequest;
import com.sekohan.sekohanback.dto.user.sign.UserSignInDTO;
import com.sekohan.sekohanback.service.user.signin.SignInService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class SignInController {


    private final SignInService signInService;

    @PostMapping("/signin")
    public JsonWebTokenResponseDTO auth(@Validated @RequestBody UserSignInDTO authRequest) {
        log.info("--------SigninController--------");

        return signInService.auth(authRequest);
    }

    @PostMapping("/refresh")
    public JsonWebTokenResponseDTO refresh(@Validated @RequestBody RefreshTokenRequest refreshTokenRequest){
        log.info("들어옴?");

        return signInService.refresh(refreshTokenRequest.getRefreshToken());
    }



}
