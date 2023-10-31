package com.sekohan.sekohanback.service.help;

import com.sekohan.sekohanback.dto.user.help.UserIdHelpDTO;
import com.sekohan.sekohanback.dto.user.change.UserPwChangeDTO;
import com.sekohan.sekohanback.dto.user.help.UserPwHelpDTO;
import com.sekohan.sekohanback.dto.user.valid.ValidCheckDTO;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.exception.NotFoundUserException;
import com.sekohan.sekohanback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class userHelpServiceImpl implements userHelpService { //유저 계정찾기 서비스

    private final UserRepository userRepository;
    
    /*아이디찾기 : 이름, 이메일 입력 -> 이메일 인증 -> 이메일 유효성 확인 ->
     인증번호 전송 -> 인증번호 체크 -> 확인버튼클릭 -> 입력한 이름,이메일로 DB 비교 ->
     일치한 ID 있으면 출력 */
    @Override
    @Transactional
    public String findLogin (UserIdHelpDTO userIdHelpDTO){
        UserEntity userEntity = userRepository.findByLogin(
                userIdHelpDTO.getEmail(), userIdHelpDTO.getName()
        ).orElseThrow(() -> NotFoundUserException.EXCEPTION);
        return userEntity.getLogin();

    }

    @Override
    @Transactional
    public boolean validEmail(ValidCheckDTO validCheckDTO){  //사용자 닉네임 유효성 체크
        return userRepository.existsByEmail(validCheckDTO.getEmail());
    }

    /* 유저 비밀번호 변경
    유저의 아이디, 이름, 이메일로 DB에 존재하는지 확인 -> 비밀번호, 비밀번호 한번 더 입력 폼에 입력
     -> 컨트롤러에서 PatchMapping 으로 비밀번호을 인코딩 후 변경*/

    @Override
    @Transactional
    public String findPassword(UserPwHelpDTO userPwHelpDTO){
        UserEntity userEntity = userRepository.findByPassword(
                userPwHelpDTO.getEmail(), userPwHelpDTO.getLogin(), userPwHelpDTO.getName()
        ).orElseThrow(() -> NotFoundUserException.EXCEPTION);
        return userEntity.getPassword();
    }
    @Override
    @Transactional
    public String passwordChange(UserPwChangeDTO userPwChangeDTO){

        //if pw1 pw2 맞는지 비교(프론트) ->
        return null;
    }

}
