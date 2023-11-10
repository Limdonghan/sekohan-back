package com.sekohan.sekohanback.dto.jwt;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessTokenBlackListDTO {

    @NotBlank
    public String accessToken;
}
