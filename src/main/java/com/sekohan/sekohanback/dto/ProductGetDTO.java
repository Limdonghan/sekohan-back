package com.sekohan.sekohanback.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ProductGetDTO {

    private Long productId;
    private String proName;
    private String proInfo;
    private int proPrice;
    private String created_time;
    private int proView;
    private byte proStatus;
    private Long catId;
    private String DetailName;
    private Long uId;
    private String nickName;
    private List<String> path;

}
