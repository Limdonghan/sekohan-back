package com.sekohan.sekohanback.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class WishListDTO {

    private long wishListId;
    private LocalDateTime localDateTime;
    private long uId;
    private long productId;
    private String title;
    private int price;
    private String info;
    private String path;

}
