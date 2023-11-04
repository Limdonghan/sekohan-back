package com.sekohan.sekohanback.service.signUp;

import com.sekohan.sekohanback.dto.user.sign.UserSignUpDTO;
import com.sekohan.sekohanback.dto.user.valid.ValidCheckDTO;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.repository.UserImageRepository;
import com.sekohan.sekohanback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor  //생성자를 자동으로 생성해주는 어노테이션
@Slf4j
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private final UserImageRepository userImageRepository;

//    @Value("${com.sekohan.upload.userProfilePath}")
//    String uploadPath;

    @Override
    @Transactional(rollbackFor = Exception.class)  //오류나면 롤백
    public void signUp(UserSignUpDTO userSignUpDTO) {
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
    public boolean validNickName(ValidCheckDTO validCheckDTO) {  //사용자 닉네임 유효성 체크 메서드
        return userRepository.existsByNickname(validCheckDTO.getNickname());
    }

    @Override
    @Transactional
    public List<UserEntity> findAllUser(){

        return userRepository.findAll();
    }


}
