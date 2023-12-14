package com.sekohan.sekohanback.dto.product;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor //
@AllArgsConstructor //
public class ProductDTO {

    private String proName;
    private int proPrice;
    private String createdDate;
    private String name;
    private String proStatus;
}