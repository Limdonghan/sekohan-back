package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.PageRequestDTO;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.security.repository.UserSecurityRepository;
import com.sekohan.sekohanback.service.event.upload.EventUploadService;
import com.sekohan.sekohanback.service.event.view.EventViewService;
import com.sekohan.sekohanback.service.user.view.UserViewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final UserSecurityRepository userSecurityRepository;
    private final EventUploadService eventUploadService;
    private final EventViewService eventViewService;
    private final UserViewService userViewService;

    /* 어드민 필터 테스트 전체 출력 */
    @GetMapping("")
    public void exAdmin(){
        UserEntity user = userSecurityRepository.getUser();
        log.info("exAdmin..........: {}",user);
    }

    /* 유저 목록 전체 출력 */
    @GetMapping("/userlist")
    public ResponseEntity userList(PageRequestDTO pageRequestDTO){
        return ResponseEntity.ok(userViewService.getList(pageRequestDTO));
    }

    /* 이벤트 업로드 */
    @PostMapping("/upload")
    public ResponseEntity localUploadEvent(
            @RequestPart("uploadFiles") MultipartFile uploadFiles) throws Exception {
        log.info("파일포스트");
        eventUploadService.uploadFile(uploadFiles);
        return ResponseEntity.ok("등록");
    }

    /* 이벤트 목록 전체 출력 */
    @GetMapping("/eventlist")
    public ResponseEntity eventList(PageRequestDTO pageRequestDTO){
        log.info("get Users");
        return ResponseEntity.ok(eventViewService.getList(pageRequestDTO));
    }

    /* 이벤트 경로 목록 출력 */
    @GetMapping("/banner")
    public ResponseEntity bannerList(PageRequestDTO pageRequestDTO){
        return ResponseEntity.ok(eventViewService.getBannerList(pageRequestDTO));
    }
}
