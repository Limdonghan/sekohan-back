package com.sekohan.sekohanback.service.event.view;

import com.sekohan.sekohanback.dto.page.PageRequestDTO;
import com.sekohan.sekohanback.dto.page.PageResultDTO;
import com.sekohan.sekohanback.dto.event.BannerDTO;
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

    /* 메인화면에 출력될 이벤트 경로 목록 */
    @Override
    public PageResultDTO<BannerDTO,EventEntity> getBannerList(PageRequestDTO pageRequestDTO){
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("eid").ascending());
        Page<EventEntity> result = eventRepository.findAll(pageable);

        Function<EventEntity, BannerDTO> fn=(eventEntity -> {
            BannerDTO build = BannerDTO.builder()
                    .path(eventEntity.getPath())
                    .build();
            return build;
        });
        return new PageResultDTO<>(result,fn);
    }


}
