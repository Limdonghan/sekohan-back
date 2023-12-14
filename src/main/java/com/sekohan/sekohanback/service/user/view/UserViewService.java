package com.sekohan.sekohanback.service.user.view;

import com.sekohan.sekohanback.dto.page.PageRequestDTO;
import com.sekohan.sekohanback.dto.page.PageResultDTO;
import com.sekohan.sekohanback.dto.user.list.UserListDTO;
import com.sekohan.sekohanback.entity.UserEntity;

public interface UserViewService {

    default UserListDTO entityToDTO(UserEntity userEntity){
        UserListDTO build = UserListDTO.builder()
                .login(userEntity.getLogin())
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .nickname(userEntity.getNickname())
                .report(String.valueOf(userEntity.getReport()))
                .build();
        return build;

    }
    PageResultDTO<UserListDTO, UserEntity> getList(PageRequestDTO pageRequestDTO);

}
