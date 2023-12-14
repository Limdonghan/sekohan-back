package com.sekohan.sekohanback.service.event.view;

import com.sekohan.sekohanback.dto.event.EventListDTO;
import com.sekohan.sekohanback.dto.event.MainPageBannerDTO;
import com.sekohan.sekohanback.dto.page.PageRequestDTO;
import com.sekohan.sekohanback.dto.page.PageResultDTO;
import com.sekohan.sekohanback.entity.EventEntity;

public interface EventViewService {

    PageResultDTO<EventListDTO,EventEntity> getList(PageRequestDTO requestDTO);

    /* ENTITY -> DTO 변환*/
    default EventListDTO entityToDto(EventEntity eventEntity){
        EventListDTO build = EventListDTO.builder()
                .eid(eventEntity.getEid())
                .name(eventEntity.getName())
                .localDateTime(eventEntity.getLocalDateTime())
                .path(eventEntity.getPath())
                .uuid(eventEntity.getUuid())
                .build();
        return build;
    }

    /* 메인화면에 출력될 이벤트 경로 목록 */
    PageResultDTO<MainPageBannerDTO,EventEntity> getBannerList(PageRequestDTO pageRequestDTO);
}
