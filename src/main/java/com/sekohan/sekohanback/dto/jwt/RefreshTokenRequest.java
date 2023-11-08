package com.sekohan.sekohanback.dto.jwt;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RefreshTokenRequest {

    /* RefreshToken 요청 */
    @NotBlank
    private String refreshToken;


}
