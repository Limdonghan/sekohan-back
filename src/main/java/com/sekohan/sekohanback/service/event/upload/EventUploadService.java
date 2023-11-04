package com.sekohan.sekohanback.service.event.upload;

import com.sekohan.sekohanback.dto.event.EventDTO;
import com.sekohan.sekohanback.entity.EventEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

public interface EventUploadService {

    @Transactional(rollbackFor = Exception.class)  //오류나면 롤백
    EventDTO uploadFile(MultipartFile uploadFiles) throws Exception;
    //dto -> entity로 변경
    default Map<String, Object> dtoToEntity(EventDTO eventDTO){
        Map<String,Object> entityMap=new HashMap<>();  //EventEntity 와 List<EventImageEntity>를 저장
        EventEntity build = EventEntity.builder()
                .uuid(eventDTO.getUuid())
                .path(eventDTO.getPath())
                .build();
        entityMap.put("event",build);
        return entityMap;
    }



}
