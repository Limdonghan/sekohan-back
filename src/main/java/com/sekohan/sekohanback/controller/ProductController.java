package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.entity.CategoryEntity;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/upload")
    public ProductEntity uploadProduct(@RequestParam("proName") String proName,
                                       @RequestParam("proPrice") int proPrice,
                                       @RequestParam("proInfo") String proInfo,
                                       @RequestParam("categoryId") long categoryId,
                                       @RequestParam("userId") long userId,
                                       @RequestParam("files") List<MultipartFile> files) {
        CategoryEntity categoryEntity = new CategoryEntity(categoryId);
        categoryEntity.setCatId(categoryId);
        UserEntity userEntity = new UserEntity(userId);
        userEntity.setUId(userId);
        return productService.uploadProduct(proName, proPrice, proInfo, categoryEntity, userEntity, files);
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(@PathVariable long productId) {
        productService.deleteProduct(productId);
    }
}