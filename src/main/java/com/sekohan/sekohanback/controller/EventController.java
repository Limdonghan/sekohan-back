package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.UploadResultDTO;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")  //나중에 경로 admin으로 바꾸기
@Slf4j
public class EventController {

    @Value("${com.sekohan.upload.path}")
    private String uploadPath;

    @PostMapping("/upload")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles){
        List<UploadResultDTO> resultDTOList =null;
        for(MultipartFile uploadfile:uploadFiles){

            resultDTOList=new ArrayList<>();
            log.info("resultDTOList : {}",resultDTOList);

            if (uploadfile.getContentType().startsWith("image") == false) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }


            String originalFilename=uploadfile.getOriginalFilename();
            log.info("originaFilename : {}",originalFilename);

            //Edge브라우저 설정, 파일경로 전부를 불러오기 때문에 필요한 부분만 가져오기위해 끝을 찾는 설정
            String fileName = originalFilename.substring(originalFilename.lastIndexOf("\\") + 1);
            log.info("filename : {}",fileName);

            String folderPath = makeFolder();
            String uuid = UUID.randomUUID().toString();

            //이미지 파일 이름
            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid+"_"+fileName;
            log.info("saveName: {}",saveName);

            Path savepath = Paths.get(saveName);
            log.info("savepath : {}",savepath);

            try {
                uploadfile.transferTo(savepath);
                String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator + uuid+"_"+fileName;
                File thumbnailFile = new File(thumbnailSaveName);
                Thumbnailator.createThumbnail(savepath.toFile(), thumbnailFile, 100, 100);
                resultDTOList.add(new UploadResultDTO(fileName,uuid,folderPath));

            } catch (IOException e) {

            }
        }
        return ResponseEntity.ok(resultDTOList);
    }

    @PutMapping("/modify")
    public ResponseEntity enentModify(){
        return null;
    }

    private String makeFolder(){
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
