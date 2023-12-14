package com.sekohan.sekohanback.service.event.view;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.sekohan.sekohanback.dto.event.EventListDTO;
import com.sekohan.sekohanback.dto.event.MainPageBannerDTO;
import com.sekohan.sekohanback.dto.page.PageRequestDTO;
import com.sekohan.sekohanback.dto.page.PageResultDTO;
import com.sekohan.sekohanback.entity.EventEntity;
import com.sekohan.sekohanback.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static com.sekohan.sekohanback.entity.QEventEntity.eventEntity;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventViewServiceImpl implements EventViewService{

    private final EventRepository eventRepository;


    /* 관리자 이벤트 목록 전체 출력 */
    @Override
    public PageResultDTO<EventListDTO, EventEntity> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("eid").descending());
        BooleanBuilder booleanBuilder = getSearch(requestDTO);
        Page<EventEntity> result = eventRepository.findAll(booleanBuilder,pageable);
        Function<EventEntity, EventListDTO> fn = (eventEntity -> entityToDto(eventEntity));
        return new PageResultDTO<>(result,fn);
    }

    public BooleanBuilder getSearch(PageRequestDTO requestDTO){
        String type = requestDTO.getType();
        String keyword = requestDTO.getKeyword();
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression = eventEntity.eid.gt(1);
        builder.and(expression);
        if(type == null || type.trim().length() == 0){
            return builder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if (type.contains("name")){
            conditionBuilder.or(eventEntity.name.contains(keyword));
        }
        return builder.and(conditionBuilder);
    }

    /* 메인화면에 출력될 이벤트 경로 목록 */
    @Override
    public PageResultDTO<MainPageBannerDTO,EventEntity> getBannerList(PageRequestDTO pageRequestDTO){
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("eid").ascending());
        Page<EventEntity> result = eventRepository.findAll(pageable);

        Function<EventEntity, MainPageBannerDTO> fn=(eventEntity -> {
            MainPageBannerDTO build = MainPageBannerDTO.builder()
                    .path(eventEntity.getPath())
                    .build();
            return build;
        });
        return new PageResultDTO<>(result,fn);
    }
}
