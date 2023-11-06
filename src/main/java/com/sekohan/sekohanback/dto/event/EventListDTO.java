package com.sekohan.sekohanback.dto.event;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor //
@AllArgsConstructor //
@Getter
@Setter
public class EventListDTO {
    private long eid;
    private String uuid;
    private String path;
    private LocalDateTime localDateTime;

}
