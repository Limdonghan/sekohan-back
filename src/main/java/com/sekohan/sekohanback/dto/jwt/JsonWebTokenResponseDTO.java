package com.sekohan.sekohanback.dto.jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class JsonWebTokenResponseDTO {
    private String message;
    private String accessToken;
    private String refreshToken;
}
