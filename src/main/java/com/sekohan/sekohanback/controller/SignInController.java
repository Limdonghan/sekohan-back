package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.jwt.AccessTokenBlackListDTO;
import com.sekohan.sekohanback.dto.jwt.JsonWebTokenResponseDTO;
import com.sekohan.sekohanback.dto.jwt.RefreshTokenRequest;
import com.sekohan.sekohanback.dto.user.sign.UserSignInDTO;
import com.sekohan.sekohanback.service.user.signin.SignInService;
import com.sekohan.sekohanback.service.user.signout.SignOutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    private final SignOutService signOutService;

    @PostMapping("/signin")
    public JsonWebTokenResponseDTO auth(@Validated @RequestBody UserSignInDTO userSignInDTO) {
        return signInService.auth(userSignInDTO);
    }

    @PostMapping("/refresh")
    public JsonWebTokenResponseDTO refresh(@Validated @RequestBody RefreshTokenRequest refreshTokenRequest){
        return signInService.refresh(refreshTokenRequest.getRefreshToken());
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@Validated @RequestBody AccessTokenBlackListDTO accessTokenBlackListDTO) {
        signOutService.logout(accessTokenBlackListDTO.getAccessToken());
        return ResponseEntity.ok("Logout Success");
    }



}
