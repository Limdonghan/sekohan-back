package com.sekohan.sekohanback.dto.user.valid;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidCheckDTO {

    @NotBlank
    private String loginId;  //사용자 ID

    @NotBlank
    private String nickname;  //사용자 닉네임

    @NotBlank
    @Email
    private String email;  //사용자 이메일
}
