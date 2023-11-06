package com.sekohan.sekohanback.service.user.view;

import com.sekohan.sekohanback.dto.PageRequestDTO;
import com.sekohan.sekohanback.dto.PageResultDTO;
import com.sekohan.sekohanback.dto.user.UserListDTO;
import com.sekohan.sekohanback.entity.UserEntity;

public interface UserViewService {

    default UserListDTO entityToDTO(UserEntity userEntity){
        UserListDTO build = UserListDTO.builder()
                .uid(userEntity.getUId())
                .login(userEntity.getLogin())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .nickname(userEntity.getNickname())
                .path(userEntity.getPath())
                .report(String.valueOf(userEntity.getReport()))
                .build();
        return build;

    }
    PageResultDTO<UserListDTO, UserEntity> getList(PageRequestDTO pageRequestDTO);
}
