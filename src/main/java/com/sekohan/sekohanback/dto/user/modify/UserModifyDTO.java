package com.sekohan.sekohanback.dto.user.modify;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor  //모든 필드 값을 파라미터로 받은 생성자 생성
@NoArgsConstructor  //매개변수가 없는 기본생성자 생성
public class UserModifyDTO {

    private String email;
    private String nickname;
}
