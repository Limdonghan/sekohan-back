package com.sekohan.sekohanback.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignInDTO {

    @NotBlank
    private String login;   //로그인 ID

    @NotBlank
    private String password; //사용자 비밀번호


}
