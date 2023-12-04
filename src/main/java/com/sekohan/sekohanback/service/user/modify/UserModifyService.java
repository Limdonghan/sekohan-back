package com.sekohan.sekohanback.service.user.modify;

import com.sekohan.sekohanback.dto.user.modify.UserModifyDTO;
import com.sekohan.sekohanback.entity.UserEntity;

public interface UserModifyService {

    default UserModifyDTO entityToDTO(UserEntity userEntity){
        UserModifyDTO build = UserModifyDTO.builder()
                .email(userEntity.getEmail())
                .nickname(userEntity.getNickname())
                .build();
        return build;
    }


    /* 회원이 회원수정을 누르면 토크으로 회원정보를 검색해서 일치하는 회원의 정보를 표시*/
    void modify(UserModifyDTO userModifyDTO);

    UserEntity getList(Long id);

}
