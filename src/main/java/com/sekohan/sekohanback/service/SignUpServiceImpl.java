package com.sekohan.sekohanback.service;

import com.sekohan.sekohanback.dto.valid.ValidCheckIdDto;
import com.sekohan.sekohanback.dto.UserSignUpDto;
import com.sekohan.sekohanback.dto.valid.ValidCheckNicknameDto;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor  //생성자를 자동으로 생성해주는 어노테이션
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(rollbackFor = Exception.class)  //오류나면 롤백
    public void signUp(UserSignUpDto userSignUpDTO) {
        String passwordEncorde=passwordEncoder.encode(userSignUpDTO.getPassword());  //사용자의 암호를 인코딩
        userSignUpDTO.setPassword(passwordEncorde);  //인코딩된 암호를 객체에 다시 설정

        Map<String, Object> entityMap = dtoToEntity(userSignUpDTO);  //메서드 호출하여 매핑처리
        UserEntity userEntity = (UserEntity) entityMap.get("user");  //user키 사용해서 추출
        userRepository.save(userEntity);  //DB저장
    }

    @Override
    @Transactional(rollbackFor = Exception.class)  //오류나면 롤백
    public boolean validID(ValidCheckIdDto userIdCheck) {
        return userRepository.existsByLogin(userIdCheck.getLoginId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)  //오류나면 롤백
    public boolean validNickName(ValidCheckNicknameDto userNicknameValidCheck) {
        return userRepository.existsByNickname(userNicknameValidCheck.getNickname());
    }

}
