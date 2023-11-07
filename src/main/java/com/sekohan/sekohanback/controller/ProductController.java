package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.ProductGetDTO;
import com.sekohan.sekohanback.dto.proImageDTO;
import com.sekohan.sekohanback.entity.CategoryEntity;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.service.product.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    @GetMapping("/pagelist")
    public Page<proImageDTO> Prolistpage(@PageableDefault(size = 12, sort = "productId", direction = Sort.Direction.DESC) Pageable pageable) {
        return productService.Prolistpage(pageable);
    }

    @PostMapping("/upload")
    public ProductEntity ProductUpload(@RequestParam("proName") String proName,
                                       @RequestParam("proPrice") int proPrice,
                                       @RequestParam("proInfo") String proInfo,
                                       @RequestParam("categoryId") long categoryId,
                                       @RequestParam("userId") long userId,
                                       @RequestPart("files") List<MultipartFile> files) {
        CategoryEntity categoryEntity = CategoryEntity.builder().catId(categoryId).build();
        UserEntity userEntity = UserEntity.builder().uId(userId).build();
        return productService.ProductUpload(proName, proPrice, proInfo, categoryEntity, userEntity, files);
    }
    //상품 업로드 URL

    @GetMapping("/list")
    public List<proImageDTO> Prolist(){
        return productService.Prolist();
    }
    //상품 리스트 URL

    @GetMapping("/list/{catId}")
    public List<proImageDTO> CatProList(@PathVariable long catId){
        return productService.CatProList(catId);
    }
    //카테고리 필터 URL

    @GetMapping("/page/{productId}")
    public ProductGetDTO getProductById(@PathVariable long productId) {
        return productService.ProductInfo(productId);
    }
    //상품 상세 페이지 URL

    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(@PathVariable long productId) {
        productService.deleteProduct(productId);
    }
    //재품 삭제 URL
}