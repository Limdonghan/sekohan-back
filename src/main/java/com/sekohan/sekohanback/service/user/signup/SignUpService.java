package com.sekohan.sekohanback.service.user.signup;

import com.sekohan.sekohanback.dto.user.sign.UserSignUpDTO;
import com.sekohan.sekohanback.dto.user.valid.ValidCheckDTO;
import com.sekohan.sekohanback.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SignUpService {

    void signUp(UserSignUpDTO userSignUpDTO);

    //사용자 ID 유효성 체크 서비스
    boolean validID(ValidCheckDTO userIdCheck);

    //사용자 이메일 유효성 체크 서비스
    boolean validNickName(ValidCheckDTO userNicknameCheck);

    @Transactional
    List<UserEntity> findAllUser();



//    api/cheker -> query string (condition == 1 || 2) -> Controller (값을 다 담아서)
//    -> Service condition == 1 ?  return userRepository.existsByLogin(userIdCheck.getLoginId()) :  return userRepository.existsByNickname(userNicknameValidCheck.getNickname());

}
