package com.sekohan.sekohanback.service.signUp;

import com.sekohan.sekohanback.dto.ValidCheckDTO;
import com.sekohan.sekohanback.dto.UserSignUpDTO;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.entity.UserRole;

import java.util.HashMap;
import java.util.Map;

public interface SignUpService {

    void signUp(UserSignUpDTO userSignUpDTO);

    //dto -> entity 변환
    default Map<String, Object>dtoToEntity(UserSignUpDTO userSignUpDTO){  //입력받은 dto를 entity로 바꿈
        Map<String, Object> entityMap =new HashMap<>();  //새로는 HashMap 객체 생성
        UserEntity userEntity = UserEntity.builder()
                .login(userSignUpDTO.getLoginId())
                .password(userSignUpDTO.getPassword())
                .name(userSignUpDTO.getName())
                .email(userSignUpDTO.getEmail())
                .nickname(userSignUpDTO.getNickname())
                .build();
        //권한 부여 default : user
        userEntity.addUserRole(UserRole.USER);


        //userEntity 객체를 entityMap에 "user"키로 반환,  userEntity 객체는 entityMap에서 "user"키를 사용하여 액세스할 수 있음
        entityMap.put("user",userEntity);

        return entityMap;
    }

    //사용자 ID 유효성 체크 서비스
    boolean validID(ValidCheckDTO userIdCheck);

    //사용자 이메일 유효성 체크 서비스
    boolean validNickName(ValidCheckDTO userNicknameCheck);



//    api/cheker -> query string (condition == 1 || 2) -> Controller (값을 다 담아서)
//    -> Service condition == 1 ?  return userRepository.existsByLogin(userIdCheck.getLoginId()) :  return userRepository.existsByNickname(userNicknameValidCheck.getNickname());

}
