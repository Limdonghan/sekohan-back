package com.sekohan.sekohanback.dto.user.modify;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor  //모든 필드 값을 파라미터로 받은 생성자 생성
@NoArgsConstructor  //매개변수가 없는 기본생성자 생성
public class UserViewDTO {

    private String path;
    private String login;
    private String email;
    private String name;
    private String nickname;

}