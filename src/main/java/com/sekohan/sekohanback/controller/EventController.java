package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.event.EventImageDTO;
import com.sekohan.sekohanback.service.event.EventUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/user")  //나중에 경로 admin으로 바꾸기
@RequiredArgsConstructor
@Slf4j
public class EventController {

    private final EventUploadService eventUploadService;

    @PostMapping("/upload")
    public ResponseEntity<List<EventImageDTO>> localUploadEvent(@RequestPart("uploadFiles") MultipartFile uploadFiles) throws Exception {
        log.info("파일포스트");
        List<EventImageDTO> eventImageDTOS = eventUploadService.uploadFile(uploadFiles);
       // eventUploadService.register(eventDTO);
        log.info("eventImageDTOS: {}",eventImageDTOS.toArray());

        System.out.println(eventImageDTOS.toString());
        return ResponseEntity.ok(eventImageDTOS);
    }
}
