package com.sekohan.sekohanback.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPwChangeDTO {
    private String password1;
    private String password2;
}
