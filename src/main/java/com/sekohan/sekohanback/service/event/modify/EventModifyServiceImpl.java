package com.sekohan.sekohanback.service.event.modify;

import com.sekohan.sekohanback.dto.event.EventModifyDTO;
import com.sekohan.sekohanback.entity.EventEntity;
import com.sekohan.sekohanback.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventModifyServiceImpl implements EventModifyService{
    private final EventRepository eventRepository;

    @Value("${com.sekohan.upload.bannerPath}")
    String uploadPath;

    @Override
    public void modify(EventModifyDTO eventModifyDTO, MultipartFile multipartFile){

        EventEntity findName = eventRepository.findByEid(eventModifyDTO.getName());
        log.info(String.valueOf(findName));

        String originalFilename=multipartFile.getOriginalFilename();
        String fileName = originalFilename.substring(originalFilename.lastIndexOf("\\") + 1);

        String folderPath=makeFolder();
        String uuid = UUID.randomUUID().toString();  //UUID 생성
        String saveName = uploadPath + File.separator +folderPath+File.separator+ uuid+"_"+fileName; //이미지 파일 이름
        Path path = Paths.get(saveName);
        String originalProfilePath = findName.getPath();
        try {
            File file = new File(originalProfilePath);
            if (file.exists()) {
                file.delete();
            }
            multipartFile.transferTo(path);
        } catch (IOException e) {
            log.info(e.toString());
        }

        if(findName.getName() != null){
            EventEntity eventEntity = findName;
            eventEntity.update(eventModifyDTO.getName2(),path.toString());
            eventRepository.save(eventEntity);
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
