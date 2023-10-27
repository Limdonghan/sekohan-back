package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.security.repository.UserSecurityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final UserSecurityRepository userSecurityRepository;
    @GetMapping("")
    public void exAdmin(){
        UserEntity user = userSecurityRepository.getUser();
        log.info("exAdmin..........: {}",user);
    }
}
