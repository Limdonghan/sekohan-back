package com.sekohan.sekohanback.service.user.help;

import com.sekohan.sekohanback.dto.user.help.UserIdHelpDTO;
import com.sekohan.sekohanback.dto.user.change.UserPwChangeDTO;
import com.sekohan.sekohanback.dto.user.help.UserPwHelpDTO;
import com.sekohan.sekohanback.dto.user.valid.ValidCheckDTO;
import org.springframework.transaction.annotation.Transactional;

public interface UserHelpService {
    /*아이디찾기 : 이름, 이메일 입력 -> 이메일 인증 -> 이메일 유효성 확인 ->
     인증번호 전송 -> 인증번호 체크 -> 확인버튼클릭 -> 입력한 이름,이메일로 DB 비교 ->
     일치한 ID 있으면 출력 */
    @Transactional
    String findLogin (UserIdHelpDTO userIdHelpDTO);

    @Transactional
    void findPassword (UserPwHelpDTO userPwHelpDTO);

    @Transactional
    boolean validEmail(ValidCheckDTO validCheckDTO);

    @Transactional
    String passwordChange(UserPwChangeDTO userPwChangeDTO, String login);
}
