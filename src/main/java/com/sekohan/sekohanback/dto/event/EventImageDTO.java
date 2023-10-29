package com.sekohan.sekohanback.dto.event;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EventImageDTO {
    private String imgName;
    private String uuid;
    private String path;

}
