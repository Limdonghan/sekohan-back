package com.sekohan.sekohanback.service.support;

import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;

public interface SupportService {

    String userSearching(String nickname);

    String productreport(ProductEntity productEntity, UserEntity userEntity);
}
