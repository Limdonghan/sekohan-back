package com.sekohan.sekohanback.dto.user.help;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserIdHelpDTO {  //ID찾기 DTO

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;
}
