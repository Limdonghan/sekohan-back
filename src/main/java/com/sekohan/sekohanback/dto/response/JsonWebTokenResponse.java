package com.sekohan.sekohanback.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class JsonWebTokenResponse {
    private String accessToken;
    private String refreshToken;
}
