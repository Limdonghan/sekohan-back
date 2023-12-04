package com.sekohan.sekohanback.dto.user.sign;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@AllArgsConstructor  //모든 필드 값을 파라미터로 받은 생성자 생성
@NoArgsConstructor  //매개변수가 없는 기본생성자 생성
public class UserSignUpDTO {  //회원가입 DTO

    @NotBlank // null , ""
    private String loginId;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nickname;

    private MultipartFile multipartFile;
}
