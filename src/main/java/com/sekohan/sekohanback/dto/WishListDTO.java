package com.sekohan.sekohanback.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WishListDTO {

    private long wishListId;
    private LocalDateTime localDateTime;
    private long uId;
    private long productId;
}
