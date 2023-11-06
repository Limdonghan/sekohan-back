package com.sekohan.sekohanback.service.event.upload;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

public interface EventUploadService {

    @Transactional(rollbackFor = Exception.class)  //오류나면 롤백
    void uploadFile(MultipartFile uploadFiles) throws Exception;

}
