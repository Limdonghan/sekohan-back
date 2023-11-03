package com.sekohan.sekohanback.service.product;

import com.sekohan.sekohanback.dto.ProductGetDTO;
import com.sekohan.sekohanback.dto.proImageDTO;
import com.sekohan.sekohanback.entity.CategoryEntity;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<proImageDTO> Prolist();

    List<proImageDTO> CatProList(long catId);

    ProductGetDTO ProductInfo(long productId);

    ProductEntity ProductUpload(String proName, int proPrice, String proInfo, CategoryEntity categoryEntity, UserEntity userEntity, List<MultipartFile> files);

    void deleteProduct(long productId);
}