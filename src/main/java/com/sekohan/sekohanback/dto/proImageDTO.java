package com.sekohan.sekohanback.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class proImageDTO {
    private Long productId;
    private String proName;
    private double proPrice;
    private String proInfo;
    private String path;
    private LocalDateTime created_date;

}