package com.sekohan.sekohanback.service.event;

import com.sekohan.sekohanback.dto.event.EventDTO;
import com.sekohan.sekohanback.dto.event.EventImageDTO;
import com.sekohan.sekohanback.entity.admin.EventEntity;
import com.sekohan.sekohanback.entity.admin.EventImageEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface EventUploadService {


    //dto -> entity로 변경
    default Map<String, Object> dtoToEntity(EventDTO eventDTO){
        Map<String,Object> entityMap=new HashMap<>();  //EventEntity 와 List<EventImageEntity>를 저장
        EventEntity eventEntity = EventEntity.builder()  //EventEntity 객체 생성
                .name(eventDTO.getEventName())
                .localDateTime(eventDTO.getLocalDateTime())
                .build();
        entityMap.put("event",eventEntity);

        List<EventImageDTO> imageDTOList = eventDTO.getEventImageDTO();
        if(imageDTOList != null && imageDTOList.size() >0){
            List<EventImageEntity> eventImageEntityList = imageDTOList.stream().map(eventImageDTO -> {
                return EventImageEntity.builder()
                        .path(eventImageDTO.getPath())
                        .originalFileName(eventImageDTO.getImgName())
                        .uuid(eventImageDTO.getUuid())
                        .eventEntity(eventEntity)
                        .build();
            }).collect(Collectors.toList());
            entityMap.put("eventImg",eventImageEntityList);
        }
        return entityMap;
    }

    @Transactional(rollbackFor = Exception.class)  //오류나면 롤백
    void register(EventDTO eventDTO);

    @Transactional(rollbackFor = Exception.class)  //오류나면 롤백
    List<EventImageDTO> uploadFile(MultipartFile uploadFiles) throws Exception;
}
