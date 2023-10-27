package com.sekohan.sekohanback.service.Authentication;

import com.sekohan.sekohanback.dto.request.AuthenticationRequest;
import com.sekohan.sekohanback.dto.response.JsonWebTokenResponse;

public interface AuthenticationService {
    JsonWebTokenResponse auth(AuthenticationRequest authRequset);
    JsonWebTokenResponse refresh(String token);
}
