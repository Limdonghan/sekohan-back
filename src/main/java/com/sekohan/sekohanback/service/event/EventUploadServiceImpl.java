package com.sekohan.sekohanback.service.event;

import com.sekohan.sekohanback.dto.event.EventDTO;
import com.sekohan.sekohanback.dto.event.EventImageDTO;
import com.sekohan.sekohanback.entity.admin.EventEntity;
import com.sekohan.sekohanback.entity.admin.EventImageEntity;
import com.sekohan.sekohanback.repository.EventImageRepository;
import com.sekohan.sekohanback.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service  //스프링 빈 등록
@RequiredArgsConstructor  //생성자 주입
@Slf4j
public class EventUploadServiceImpl implements EventUploadService{

    private final EventImageRepository eventImageRepository;
    private final EventRepository eventRepository;

    @Value("${com.sekohan.upload.path}")
    String uploadPath;


    @Override
    @Transactional(rollbackFor = Exception.class)  //오류나면 롤백
    public void register(EventDTO eventDTO) {

        log.info("DTO를 ENTITY로 변경");
        Map<String, Object> entityMap=dtoToEntity(eventDTO);
        log.info("entityMap1 : {}",entityMap.toString());

        EventEntity eventEntity= (EventEntity) entityMap.get("event");
        log.info("eventEntity : {}",eventEntity.toString());

        List<EventImageEntity> eventImageList = (List<EventImageEntity>) entityMap.get("eventImg");
        log.info("entityMap3 : {}",entityMap.toString());
        log.info("imageEntityList : {}",eventImageList);

        log.info("DB저장");
        eventRepository.save(eventEntity);  //DB저장
        eventImageList.forEach(eventImageEntity -> eventImageRepository.save(eventImageEntity));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)  //오류나면 롤백
    public List<EventImageDTO> uploadFile(MultipartFile uploadFiles) throws Exception {
        log.info("파일업로드");
        List<EventImageDTO> resultDTOList = new ArrayList<>();

            log.info("uploadfile : {}",uploadFiles);

            if (uploadFiles.getContentType().startsWith("image") == false) {
                throw new Exception("잘못된 파일 형식입니다.");
            }

            String originalFilename=uploadFiles.getOriginalFilename();

            //Edge브라우저 설정, 파일경로 전부를 불러오기 때문에 필요한 부분만 가져오기위해 끝을 찾는 설정
            String fileName = originalFilename.substring(originalFilename.lastIndexOf("\\") + 1);
            String folderPath = makeFolder();  //폴더 생성
            String uuid = UUID.randomUUID().toString();  //UUID 생성
            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid+"_"+fileName; //이미지 파일 이름
            Path path = Paths.get(saveName);
            log.info("path : {}",path);
            try {
                uploadFiles.transferTo(path);
                String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator + "s_" + uuid + "_" + fileName;
                File thumbnailFile = new File(thumbnailSaveName);
                Thumbnailator.createThumbnail(path.toFile(), thumbnailFile, 100, 100);
                resultDTOList.add(new EventImageDTO(fileName, uuid, folderPath));
            } catch (IOException e) {}

        EventEntity save = eventRepository.save(EventEntity.builder()
                .name("이벤트")
                .localDateTime(LocalDateTime.now())
                .build());
        eventImageRepository.save(EventImageEntity.builder()
                        .originalFileName(fileName)
                        .path(folderPath)
                        .uuid(uuid)
                        .eventEntity(save)
                        .build()
            );
        log.info("resultDTOList : {}",resultDTOList.toString());
        return resultDTOList ;
    }

    private String makeFolder(){  //파일 생성 메서드
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        log.info("날짜 생성 : {}",str);
        String folderPath = str.replace("//", File.separator);
        log.info("폴더 생성 : {}",folderPath);
        File uploadPathFolder = new File(uploadPath, folderPath);
        log.info("업로드 경로 폴더 : {}",uploadPathFolder);
        if (uploadPathFolder.exists() == false) {
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }
}
