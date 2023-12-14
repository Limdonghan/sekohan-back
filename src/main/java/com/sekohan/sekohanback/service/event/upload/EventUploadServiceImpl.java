package com.sekohan.sekohanback.service.event.upload;

import com.sekohan.sekohanback.dto.event.BannerNameDTO;
import com.sekohan.sekohanback.entity.EventEntity;
import com.sekohan.sekohanback.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service  //스프링 빈 등록
@RequiredArgsConstructor  //생성자 주입
@Slf4j
public class EventUploadServiceImpl implements EventUploadService{

    private final EventRepository eventRepository;

    @Value("${com.sekohan.upload.bannerPath}")
    String uploadPath;


    @Override
    @Transactional(rollbackFor = Exception.class)  //오류나면 롤백
    public void uploadFile(MultipartFile uploadFiles, BannerNameDTO bannerNameDTO) throws Exception {

        if (!uploadFiles.getContentType().startsWith("image/")) {
                throw new Exception("잘못된 파일 형식 입니다.");
            }
            String originalFilename=uploadFiles.getOriginalFilename();

            //Edge브라우저 설정, 파일 경로 전부를 불러오기 때문에 필요한 부분만 가져오기 위해 끝을 찾는 설정
            String fileName = originalFilename.substring(originalFilename.lastIndexOf("\\") + 1);

            String folderPath = makeFolder();  //폴더 생성
            String uuid = UUID.randomUUID().toString();  //UUID 생성
            String saveName = uploadPath + File.separator +folderPath+File.separator+ uuid+"_"+fileName; //이미지 파일 이름
            Path path = Paths.get(saveName);
            try {
                uploadFiles.transferTo(path);
                eventRepository.save(
                        EventEntity.builder()
                                .name(bannerNameDTO.getBannerName())
                                .path(saveName)
                                .uuid(uuid)
                                .build()
                );
            } catch (IOException e) {
                log.info(e.toString());
            }
    }
    private String makeFolder(){  //파일 생성 메서드
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("//", File.separator);
        File uploadPathFolder = new File(uploadPath, folderPath);
        if (uploadPathFolder.exists() == false) {
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }
}
