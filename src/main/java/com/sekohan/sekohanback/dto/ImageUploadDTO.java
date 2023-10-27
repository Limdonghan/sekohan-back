package com.sekohan.sekohanback.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDTO {
    private MultipartFile file;


}
