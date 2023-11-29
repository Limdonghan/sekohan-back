package com.sekohan.sekohanback.service.user.signup;

import com.sekohan.sekohanback.dto.user.sign.UserSignUpDTO;
import com.sekohan.sekohanback.dto.user.valid.ValidCheckDTO;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.entity.UserRole;
import com.sekohan.sekohanback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor  //생성자를 자동으로 생성해주는 어노테이션
@Slf4j
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;

    @Value("${com.sekohan.upload.userProfilePath}")
    String uploadPath;

    @Value("${com.sekohan.upload.bassics}")
    String bassicsPath;

    @Override
    @Transactional(rollbackFor = Exception.class)  //오류나면 롤백
    public void signUp(UserSignUpDTO userSignUpDTO) {
        String profilePath = "";
        if(userSignUpDTO.getMultipartFile().isEmpty()) {
            profilePath = bassicsPath.concat(File.separator+"basics.png");
        }
        else {
            if (userSignUpDTO.getMultipartFile().getContentType().startsWith("image") == false) {
//                throw new Exception("잘못된 파일 형식 입니다.");
            }
            String folderPath=makeFolder(userSignUpDTO);  //폴더생성
            String saveName = uploadPath + File.separator + folderPath+File.separator + userSignUpDTO.getMultipartFile().getOriginalFilename();
            Path path = Paths.get(saveName);
            profilePath = saveName;
            try {
                userSignUpDTO.getMultipartFile().transferTo(path);
            } catch (IOException e) {
                log.info(e.toString());
            }
        }

        UserEntity build = UserEntity.builder()
                .login(userSignUpDTO.getLoginId())
                .password(userSignUpDTO.getPassword())
                .name(userSignUpDTO.getName())
                .nickname(userSignUpDTO.getNickname())
                .email(userSignUpDTO.getEmail())
                .path(profilePath)
                .userRole(UserRole.USER)
                .build();
        userRepository.save(build);
    }

    private String makeFolder(UserSignUpDTO userSignUpDTO){  //파일 생성 메서드
        String name = userSignUpDTO.getLoginId();
        //String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = name.replace("//", File.separator);
        File uploadPathFolder = new File(uploadPath, folderPath);
        if (uploadPathFolder.exists() == false) {  //폴더가 존재하면 만들지 생성안함
            uploadPathFolder.mkdirs();
        }
        return folderPath;
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

}
