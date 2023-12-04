package com.sekohan.sekohanback.service.user.signup;

import com.sekohan.sekohanback.dto.user.sign.UserSignUpDTO;
import com.sekohan.sekohanback.dto.user.valid.ValidCheckDTO;

public interface SignUpService {

    void signUp(UserSignUpDTO userSignUpDTO);

    //사용자 ID 유효성 체크 서비스
    boolean validID(ValidCheckDTO userIdCheck);

    //사용자 이메일 유효성 체크 서비스
    boolean validNickName(ValidCheckDTO userNicknameCheck);
}
