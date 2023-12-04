package com.sekohan.sekohanback.dto.user.change;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPwChangeDTO {
    @NotBlank
    private String password1;

    @NotBlank
    private String password2;


}
