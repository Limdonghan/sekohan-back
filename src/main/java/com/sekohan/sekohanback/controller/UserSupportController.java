package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.service.support.SupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sup")
@RequiredArgsConstructor
public class UserSupportController {

    private final SupportService supportService;

    @PostMapping("/report")
    public String productreport(@RequestParam("productId") long productId,
                                     @RequestParam("userId") long userId) {
        ProductEntity productEntity = ProductEntity.builder().productId(productId).build();
        UserEntity userEntity = UserEntity.builder().uId(userId).build();
        return supportService.productreport(productEntity, userEntity);
    }
}
