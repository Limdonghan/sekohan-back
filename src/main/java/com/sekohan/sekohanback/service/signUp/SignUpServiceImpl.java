package com.sekohan.sekohanback.service.signUp;

import com.sekohan.sekohanback.dto.user.UserSignUpDTO;
import com.sekohan.sekohanback.dto.user.ValidCheckDTO;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor  //생성자를 자동으로 생성해주는 어노테이션
@Slf4j
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)  //오류나면 롤백
    public void signUp(UserSignUpDTO userSignUpDTO) {
       // String passwordEncorde=passwordEncoder.encode(userSignUpDTO.getPassword());  //사용자의 암호를 인코딩
       // userSignUpDTO.setPassword(passwordEncorde);  //인코딩된 암호를 객체에 다시 설정

        Map<String, Object> entityMap = dtoToEntity(userSignUpDTO);  //메서드 호출하여 매핑처리
        log.info("entityMAP : {}", entityMap);
        UserEntity userEntity = (UserEntity) entityMap.get("user");  //user키 사용해서 추출
        log.info("userEntity : {}", userEntity);
        userRepository.save(userEntity);  //DB저장
    }

    @Override
    @Transactional(rollbackFor = Exception.class)  //오류나면 롤백
    public boolean validID(ValidCheckDTO validCheckDTO) {  //사용자 ID 유효성 체크 메서드
        return userRepository.existsByLogin(validCheckDTO.getLoginId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)  //오류나면 롤백
    public boolean validNickName(ValidCheckDTO userNicknameCheck) {  //사용자 닉네임 유효성 체크 메서드
        return userRepository.existsByNickname(userNicknameCheck.getNickname());
    }


}
