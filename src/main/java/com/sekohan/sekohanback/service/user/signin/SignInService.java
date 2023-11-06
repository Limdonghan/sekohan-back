package com.sekohan.sekohanback.service.user.signin;

import com.sekohan.sekohanback.dto.user.sign.UserSignInDTO;
import com.sekohan.sekohanback.dto.jwt.JsonWebTokenResponseDTO;

public interface SignInService {
    JsonWebTokenResponseDTO auth(UserSignInDTO authRequset);
    JsonWebTokenResponseDTO refresh(String token);
}