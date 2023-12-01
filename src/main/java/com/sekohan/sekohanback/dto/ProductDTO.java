package com.sekohan.sekohanback.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ProductDTO {
    private Long productId;
    private String proName;
    private int proPrice;
    private String proInfo;


}