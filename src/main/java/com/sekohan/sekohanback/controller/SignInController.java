package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.request.AuthenticationRequest;
import com.sekohan.sekohanback.dto.response.JsonWebTokenResponse;
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
    public JsonWebTokenResponse auth(@Validated @RequestBody AuthenticationRequest authRequest) {
        log.info("--------SigninController--------");

        return authenticationService.auth(authRequest);
    }

    @GetMapping("/member")
    public void exMember(){
        UserEntity user = userSecurityRepository.getUser();
        log.info("exMember.......... : {}",user);
    }

}
