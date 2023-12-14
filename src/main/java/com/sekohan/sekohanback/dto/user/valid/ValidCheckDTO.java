package com.sekohan.sekohanback.dto.user.valid;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidCheckDTO {

    private String loginId;  //사용자 ID

    private String nickname;  //사용자 닉네임

    @Email
    private String email;  //사용자 이메일
}
