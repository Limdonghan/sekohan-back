package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.ProductGetDTO;
import com.sekohan.sekohanback.dto.proImageDTO;
import com.sekohan.sekohanback.entity.CategoryEntity;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/upload")
    public ProductEntity uploadProduct(@RequestParam("proName") String proName,
                                       @RequestParam("proPrice") int proPrice,
                                       @RequestParam("proInfo") String proInfo,
                                       @RequestParam("categoryId") long categoryId,
                                       @RequestParam("userId") long userId,
                                       @RequestPart("files") List<MultipartFile> files) {
        CategoryEntity categoryEntity = CategoryEntity.builder().catId(categoryId).build();
        UserEntity userEntity = UserEntity.builder().uId(userId).build();
        return productService.uploadProduct(proName, proPrice, proInfo, categoryEntity, userEntity, files);
    }
    //상품 업로드 URL


    @GetMapping("/list")
    public List<proImageDTO> getAllList(){
        return productService.listProduct();
    }
    //상품 리스트 URL

    @GetMapping("/list/{catId}")
    public List<proImageDTO> getcatList(@PathVariable long catId){
        return productService.getcatList(catId);
    }
    //카테고리 필터 URL

    @GetMapping("/page/{productId}")
    public ProductGetDTO getProductById(@PathVariable long productId) {
        return productService.getProductById(productId);
    }
    //상품 상세 페이지 URL

    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(@PathVariable long productId) {
        productService.deleteProduct(productId);
    }
    //재품 삭제 URL
}