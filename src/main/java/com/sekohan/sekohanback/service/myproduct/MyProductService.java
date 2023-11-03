package com.sekohan.sekohanback.service.myproduct;

import com.sekohan.sekohanback.dto.proImageDTO;
import com.sekohan.sekohanback.entity.CategoryEntity;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MyProductService {
    List<proImageDTO> UserProList(long uId);

    List<proImageDTO> UserProSoldoutList(long uId);


    ProductEntity updateProduct(long productId, String proName, int proPrice, String proInfo, UserEntity userEntity, CategoryEntity categoryEntity, byte status, List<MultipartFile> files);
}
