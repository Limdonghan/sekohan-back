package com.sekohan.sekohanback.service.user.modify;

import com.sekohan.sekohanback.dto.user.modify.UserModifyDTO;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.repository.UserRepository;
import com.sekohan.sekohanback.security.repository.UserSecurityRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserModifyServiceImpl implements UserModifyService{
    private final UserRepository userRepository;
    private final UserSecurityRepositoryImpl userSecurityRepository;

    @Value("${com.sekohan.upload.userProfilePath}")
    String uploadPath;

    /* 회원이 회원수정을 누르면 토크으로 회원정보를 검색해서 일치하는 회원의 정보를 표시*/
    @Override
    public void modify(UserModifyDTO userModifyDTO){
        UserEntity user = userSecurityRepository.getUser();

        UserEntity result = userRepository.findByLogin2(user.getLogin());

        String folderPath=makeFolder();
        String saveName = uploadPath + File.separator + folderPath+File.separator + userModifyDTO.getMultipartFile().getOriginalFilename(); // 새로 저장
        Path path = Paths.get(saveName);
        String originalProfilePath = result.getPath();

        try {
            File file = new File(originalProfilePath);
            if (file.exists()) {
                file.delete();
            }
            userModifyDTO.getMultipartFile().transferTo(path);
        } catch (IOException e) {
            log.info(e.toString());
        }

        if(result.getEmail() == null){
            UserEntity userEntity = result;
            userEntity.update(userModifyDTO.getNickname(),userModifyDTO.getEmail(), path.toString());
            userRepository.save(userEntity);
        }
    }

    @Override
    public UserEntity getList(Long id){
        UserEntity byUId = userRepository.findByUId(id);
        return byUId;
    }

    private String makeFolder(){  //파일 생성 메서드
        UserEntity user = userSecurityRepository.getUser();
        String name = user.getLogin();
        String folderPath = name.replace("//", File.separator);
        File uploadPathFolder = new File(uploadPath, folderPath);
        if (uploadPathFolder.exists() == false) {  //폴더가 존재하면 만들지 생성안함
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }
}
