package com.sekohan.sekohanback.service.support;

import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;

public interface SupportService {

    void userSearching(Long uId);

    String productreport(ProductEntity productEntity, UserEntity userEntity);
}
