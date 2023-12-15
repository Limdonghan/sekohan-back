package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.event.BannerNameDTO;
import com.sekohan.sekohanback.dto.event.EventModifyDTO;
import com.sekohan.sekohanback.dto.page.PageRequestDTO;
import com.sekohan.sekohanback.service.event.delete.EventDeleteService;
import com.sekohan.sekohanback.service.event.modify.EventModifyService;
import com.sekohan.sekohanback.service.event.upload.EventUploadService;
import com.sekohan.sekohanback.service.event.view.EventViewService;
import com.sekohan.sekohanback.service.product.ProService;
import com.sekohan.sekohanback.service.user.view.UserViewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/a")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final EventUploadService eventUploadService;
    private final EventViewService eventViewService;
    private final EventDeleteService eventDeleteService;
    private final EventModifyService eventModifyService;
    private final UserViewService userViewService;
    private final ProService proService;

    /* 유저 목록 */
    @GetMapping("/userlist")
    public ResponseEntity userList(PageRequestDTO pageRequestDTO){
        return ResponseEntity.ok(userViewService.getList(pageRequestDTO));
    }

    /* 이벤트 업로드 */   //보완 할 것   
    @PostMapping("/upload")
    public ResponseEntity localUploadEvent(
            @RequestPart("UploadFiles") MultipartFile uploadFiles,
             BannerNameDTO bannerNameDTO) throws Exception {
        eventUploadService.uploadFile(uploadFiles,bannerNameDTO);
        return ResponseEntity.ok("등록");
    }

    /* 이벤트 목록 전체 출력 */
    @GetMapping("/eventlist")
    public ResponseEntity eventList(PageRequestDTO pageRequestDTO){
        return ResponseEntity.ok(eventViewService.getList(pageRequestDTO));
    }

    /* 이벤트 수정 */
    @PutMapping("/update")
    public ResponseEntity update(@RequestParam("multipartFile") MultipartFile multipartFile,
                                 @Validated EventModifyDTO eventModifyDTO){
        eventModifyService.modify(eventModifyDTO,multipartFile);
        return ResponseEntity.ok("수정완료");
    }

    /* 이벤트 삭체 */
    @DeleteMapping("/eventDelete/{eid}")
    public ResponseEntity removeEvent(@PathVariable("eid") long eid){
        eventDeleteService.remove(eid);
        return ResponseEntity.ok("삭제완료");

    }

    /*상품 목록*/
    @GetMapping("/prolist")
    public ResponseEntity proList(PageRequestDTO pageRequestDTO){
        return ResponseEntity.ok(proService.getList(pageRequestDTO));
    }

}
