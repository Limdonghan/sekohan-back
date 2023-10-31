package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.proImageDTO;
import com.sekohan.sekohanback.entity.CategoryEntity;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.service.MyProductService;
import com.sekohan.sekohanback.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/myproducts")
@RequiredArgsConstructor
public class MyProductController {

    @Autowired
    private MyProductService MyproductService;

    @GetMapping("/list/{uId}")
    public List<proImageDTO> getuserproduct(@PathVariable long uId) {
        return MyproductService.getuserproduct(uId);
    }

    @PutMapping("/update/{ProductId}")
    public ProductEntity updateProduct(@PathVariable long ProductId,
                                       @RequestParam("proName") String proName,
                                       @RequestParam("proPrice") int proPrice,
                                       @RequestParam("proInfo") String proInfo,
                                       @RequestParam("status") byte status,
                                       @RequestParam("categoryId") long categoryId,
                                       @RequestParam("userId") long userId,
                                       @RequestPart("files") List<MultipartFile> files) {
        CategoryEntity categoryEntity = new CategoryEntity(categoryId);
        categoryEntity.setCatId(categoryId);
        UserEntity userEntity = new UserEntity(userId);
        userEntity.setUId(userId);
        return MyproductService.updateProduct(ProductId, proName, proPrice, proInfo,userEntity, categoryEntity ,status, files);
    }

}
