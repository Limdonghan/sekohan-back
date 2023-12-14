package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.user.modify.UserModifyDTO;
import com.sekohan.sekohanback.service.user.modify.UserModifyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/my")
@RequiredArgsConstructor
public class UserModifyController {
    private final UserModifyService userModifyService;

    @GetMapping("")
    public ResponseEntity updateForm(){

        return ResponseEntity.ok(userModifyService.getList());
    }

    @PutMapping("/update")
    public ResponseEntity update(UserModifyDTO userModifyDTO){
        userModifyService.modify(userModifyDTO);
        return ResponseEntity.ok(userModifyDTO);
    }
}
