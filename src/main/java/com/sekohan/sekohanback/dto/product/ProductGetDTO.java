package com.sekohan.sekohanback.dto.product;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ProductGetDTO {

    private Long productId;
    private String proName;
    private String proInfo;
    private int proPrice;
    private LocalDateTime created_time;
    private int proView;
    private byte proStatus;
    private Long catId;
    private String DetailName;
    private Long uId;
    private String nickName;
    private List<String> path;

}