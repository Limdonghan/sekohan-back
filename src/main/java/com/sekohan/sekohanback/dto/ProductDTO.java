package com.sekohan.sekohanback.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDTO {
    private Long productId;
    private String proName;
    private int proPrice;
    private String proInfo;


}