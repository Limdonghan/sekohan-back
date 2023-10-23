package com.sekohan.sekohanback.service;

import com.sekohan.sekohanback.dto.valid.ValidCheckIdDto;
import com.sekohan.sekohanback.dto.UserSignUpDto;
import com.sekohan.sekohanback.dto.valid.ValidCheckNicknameDto;
import com.sekohan.sekohanback.entity.UserEntity;

import java.util.HashMap;
import java.util.Map;

public interface SignUpService {

    void signUp(UserSignUpDto userSignUpDTO);

    //dto -> entity 변환
    default Map<String, Object>dtoToEntity(UserSignUpDto userSignUpDTO){  //입력받은 dto를 entity로 바꿈
        Map<String, Object> entityMap =new HashMap<>();  //새로는 HashMap 객체 생성
        UserEntity userEntity = UserEntity.builder()
                .login(userSignUpDTO.getLoginId())
                .password(userSignUpDTO.getPassword())
                .name(userSignUpDTO.getName())
                .email(userSignUpDTO.getEmail())
                .nickname(userSignUpDTO.getNickname())
                .build();

        //userEntity 객체를 entityMap에 "user"키로 반환,  userEntity 객체는 entityMap에서 "user"키를 사용하여 액세스할 수 있음
        entityMap.put("user",userEntity);

        return entityMap;
    }

    //사용자 ID 유효성 체크 서비스
    boolean validID(ValidCheckIdDto userIdCheck);

    //사용자 이메일 유효성 체크 서비스
    boolean validNickName(ValidCheckNicknameDto userNicknameValidCheck);
}
