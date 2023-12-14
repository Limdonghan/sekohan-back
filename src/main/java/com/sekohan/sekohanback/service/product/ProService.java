package com.sekohan.sekohanback.service.product;

import com.sekohan.sekohanback.dto.page.PageRequestDTO;
import com.sekohan.sekohanback.dto.page.PageResultDTO;
import com.sekohan.sekohanback.dto.product.ProductDTO;
import com.sekohan.sekohanback.entity.ProductEntity;

public interface ProService {

    default ProductDTO entityToDTO(ProductEntity productEntity){
        return ProductDTO.builder()
                .proName(productEntity.getProName())
                .proPrice(productEntity.getProPrice())
                .createdDate(String.valueOf(productEntity.getLocalDateTime()))
                .proStatus(String.valueOf(productEntity.getProStatus()))
                .name(productEntity.getUserEntity().getName())
                .build();
    }

    PageResultDTO<ProductDTO, ProductEntity> getList(PageRequestDTO pageRequestDTO);
}
