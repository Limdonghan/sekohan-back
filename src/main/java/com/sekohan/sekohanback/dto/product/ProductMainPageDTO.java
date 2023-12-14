package com.sekohan.sekohanback.dto.product;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor //
@AllArgsConstructor //
public class ProductMainPageDTO {  //메인화면에 사용되는 DTO
    //private String path;
    private String proName;
    private int proPrice;
    private int proView;
    private String proDate;
}
