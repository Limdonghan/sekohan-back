package com.sekohan.sekohanback.service.event.view;

import com.sekohan.sekohanback.dto.PageRequestDTO;
import com.sekohan.sekohanback.dto.PageResultDTO;
import com.sekohan.sekohanback.dto.event.EventDTO;
import com.sekohan.sekohanback.dto.event.EventListDTO;
import com.sekohan.sekohanback.entity.EventEntity;
import com.sekohan.sekohanback.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventViewServiceImpl implements EventViewService{

    private final EventRepository eventRepository;


    /* 이벤트 목록 전체 출력 */
    @Override
    public PageResultDTO<EventListDTO, EventEntity> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("eid").descending());

        Page<EventEntity> result = eventRepository.findAll(pageable);

        Function<EventEntity, EventListDTO> fn = (eventEntity -> entityToDto(eventEntity));
        return new PageResultDTO<>(result,fn);
    }


}
