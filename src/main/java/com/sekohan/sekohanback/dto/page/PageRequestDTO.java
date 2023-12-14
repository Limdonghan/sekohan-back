package com.sekohan.sekohanback.dto.page;

import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class PageRequestDTO {  //페이지 요청 처리 DTO
    private int page;  //페이지 번호
    private int size;  //목록 갯수
    private String type; //검색 타입
    private String keyword;  //검색 키워드

    public Pageable getPageable(Sort sort){  //Pageable 객체 생성
        //페이지가 0부터 시작함
        return PageRequest.of(page -1,size,sort);
    }
}
