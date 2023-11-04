package com.sekohan.sekohanback.service.authentication;

import com.sekohan.sekohanback.dto.user.sign.UserSignInDTO;
import com.sekohan.sekohanback.dto.jwt.JsonWebTokenResponseDTO;

public interface AuthenticationService {
    JsonWebTokenResponseDTO auth(UserSignInDTO authRequset);
    JsonWebTokenResponseDTO refresh(String token);
}