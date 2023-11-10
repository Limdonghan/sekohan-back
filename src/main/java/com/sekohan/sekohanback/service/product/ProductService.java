package com.sekohan.sekohanback.service.product;

import com.sekohan.sekohanback.dto.ProductGetDTO;
import com.sekohan.sekohanback.dto.proImageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<proImageDTO> Prolistpage(Pageable pageable);

    Page<proImageDTO> CatProlistpage(long catId, Pageable pageable);

    /*
    List<proImageDTO> Prolist();

    List<proImageDTO> CatProList(long catId);
     */

    ProductGetDTO ProductInfo(long productId);


    void deleteProduct(long productId);
}