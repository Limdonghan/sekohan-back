package com.sekohan.sekohanback.dto.user.sign;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor  //모든 필드 값을 파라미터로 받은 생성자 생성
@NoArgsConstructor  //매개변수가 없는 기본생성자 생성
public class UserSignUpDTO {  //회원가입 DTO

    private String loginId;
    private String password;
    private String name;
    private String email;
    private String nickname;


}
