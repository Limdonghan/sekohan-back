package com.sekohan.sekohanback.dto.event;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor //
@AllArgsConstructor //
@Getter
@Setter
public class EventDTO {
    private String name;

    private String uuid;

    private String path;

    private LocalDateTime localDateTime;

    private MultipartFile multipartFile;

}
