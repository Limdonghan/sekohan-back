package com.sekohan.sekohanback.service.Authentication;

import com.sekohan.sekohanback.dto.user.UserSignInDTO;
import com.sekohan.sekohanback.dto.jwt.JsonWebTokenResponseDTO;

public interface AuthenticationService {
    JsonWebTokenResponseDTO auth(UserSignInDTO authRequset);
    JsonWebTokenResponseDTO refresh(String token);
}
