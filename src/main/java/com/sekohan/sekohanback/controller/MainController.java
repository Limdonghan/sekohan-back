package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.page.PageRequestDTO;
import com.sekohan.sekohanback.service.event.view.EventViewService;
import com.sekohan.sekohanback.service.main.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor //생성자를 자동으로 생성해주는 어노테이션
@RequestMapping("/api/main")
public class MainController {
    private final EventViewService eventViewService;
    private final MainService mainService;

    /* 배너 경로 목록 출력 */
    @GetMapping("/banner")
    public ResponseEntity bannerList(PageRequestDTO pageRequestDTO){
        return ResponseEntity.ok(eventViewService.getBannerList(pageRequestDTO));
    }

    /*추천순 상품 리스트*/
    @GetMapping("/view")
    public ResponseEntity productViewList(PageRequestDTO pageRequestDTO){
        return ResponseEntity.ok(mainService.viewList(pageRequestDTO));
    }

    /*최신순 상품 리스트*/
    @GetMapping("/date")
    public ResponseEntity productDateList(PageRequestDTO pageRequestDTO){
        return ResponseEntity.ok(mainService.dateList(pageRequestDTO));
    }

    /*상품 검색*/
    @GetMapping("/search")
    public ResponseEntity productSearch(PageRequestDTO pageRequestDTO){
        return ResponseEntity.ok(mainService.search(pageRequestDTO));
    }
}
