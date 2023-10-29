package com.sekohan.sekohanback.dto.event;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor //
@AllArgsConstructor //
@Getter
@Setter
public class EventDTO {
    private String eventID;
    private String eventName;

    private LocalDateTime localDateTime;
    
    @Builder.Default  //초기화
    private List<EventImageDTO> eventImageDTO = new ArrayList<>();


}
