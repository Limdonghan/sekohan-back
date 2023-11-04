package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.user.UserListDTO;
import com.sekohan.sekohanback.service.event.upload.EventUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/user")  //나중에 경로 admin으로 바꾸기
@RequiredArgsConstructor
@Slf4j
public class EventController {

    private final EventUploadService eventUploadService;

    @PostMapping("/upload")
    public ResponseEntity localUploadEvent(
            @RequestPart("uploadFiles") MultipartFile uploadFiles) throws Exception {
        log.info("파일포스트");
        eventUploadService.uploadFile(uploadFiles);
        return ResponseEntity.ok("등록");
    }

    @GetMapping("/userList")
    public List<UserListDTO> userList(Model model){
        log.info("get Users");

        return null;
    }



}
