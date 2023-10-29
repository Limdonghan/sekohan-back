package com.sekohan.sekohanback.service.event;

import com.sekohan.sekohanback.dto.event.EventDTO;
import com.sekohan.sekohanback.entity.admin.EventEntity;
import com.sekohan.sekohanback.entity.admin.EventImageEntity;
import com.sekohan.sekohanback.repository.EventImageRepository;
import com.sekohan.sekohanback.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service  //스프링 빈 등록
@RequiredArgsConstructor  //생성자 주입
public class EventUploadServiceImpl implements EventUploadService{

    private final EventImageRepository eventImageRepository;
    private final EventRepository eventRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)  //오류나면 롤백
    public void register(EventDTO eventDTO) {
        Map<String, Object> entityMap=dtoToEntity(eventDTO);
        EventEntity eventEntity= (EventEntity) entityMap.get("event");
        List<EventImageEntity> imageEntityList = (List<EventImageEntity>) entityMap.get("eventImg");

        eventRepository.save(eventEntity);
        imageEntityList.forEach(eventImageEntity -> eventImageRepository.save(eventImageEntity));
    }
}
