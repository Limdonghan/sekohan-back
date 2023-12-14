package com.sekohan.sekohanback.dto.product;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor //
@AllArgsConstructor //
public class ProductSearchDTO {
    private String proName;
    private int proPrice;
    private int proView;
    private String proDate;
    private String state;
}
