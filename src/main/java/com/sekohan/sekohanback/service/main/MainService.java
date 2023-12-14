package com.sekohan.sekohanback.service.main;

import com.sekohan.sekohanback.dto.page.PageRequestDTO;
import com.sekohan.sekohanback.dto.page.PageResultDTO;
import com.sekohan.sekohanback.dto.product.ProductMainPageDTO;
import com.sekohan.sekohanback.dto.product.ProductSearchDTO;
import com.sekohan.sekohanback.entity.ProductEntity;

public interface MainService {

    PageResultDTO<ProductMainPageDTO, ProductEntity> viewList(PageRequestDTO pageRequestDTO);

    PageResultDTO<ProductMainPageDTO, ProductEntity> dateList(PageRequestDTO pageRequestDTO);

    default ProductMainPageDTO entityToDTO(ProductEntity productEntity) {
        return ProductMainPageDTO.builder()
                .proName(productEntity.getProName())
                .proPrice(productEntity.getProPrice())
                .proView(productEntity.getProView())
                .proDate(productEntity.getLocalDateTime().toString())
                .build();
    }

    PageResultDTO<ProductSearchDTO, ProductEntity> search(PageRequestDTO pageRequestDTO);

    default ProductSearchDTO entityToDTOS(ProductEntity productEntity) {
        return  ProductSearchDTO.builder()
                .proName(productEntity.getProName())
                .proPrice(productEntity.getProPrice())
                .proView(productEntity.getProView())
                .proDate(productEntity.getLocalDateTime().toString())
                .state(String.valueOf(productEntity.getProStatus()))
                .build();
    }
}
