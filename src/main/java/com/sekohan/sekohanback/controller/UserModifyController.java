package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.user.UserModifyDTO;
import com.sekohan.sekohanback.jwt.service.JwtService;
import com.sekohan.sekohanback.service.user.modify.UserModifyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/user/update")
@RequiredArgsConstructor
public class UserModifyController {
    private final UserModifyService userModifyService;
    private final JwtService jwtService;

    @GetMapping("/{id}")
    public ResponseEntity updateForm(@PathVariable Long id){
        return ResponseEntity.ok(userModifyService.getList(id));
    }

    @PutMapping("")
    public ResponseEntity update(UserModifyDTO userModifyDTO){
        userModifyService.modify(userModifyDTO);
        return ResponseEntity.ok(userModifyDTO);
    }
}
