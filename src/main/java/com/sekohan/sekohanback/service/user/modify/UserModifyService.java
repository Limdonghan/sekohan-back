package com.sekohan.sekohanback.service.user.modify;

import com.sekohan.sekohanback.dto.user.modify.UserModifyDTO;
import com.sekohan.sekohanback.dto.user.modify.UserViewDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UserModifyService {

    /* 회원이 회원수정을 누르면 토크으로 회원정보를 검색해서 일치하는 회원의 정보를 표시*/
    void modify(UserModifyDTO userModifyDTO, MultipartFile multipartFile);


    UserViewDTO getList();
}
