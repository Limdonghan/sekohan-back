package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.ProductGetDTO;
import com.sekohan.sekohanback.dto.proImageDTO;
import com.sekohan.sekohanback.service.product.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    @GetMapping("/list")
    public Page<proImageDTO> Prolistpage(@PageableDefault(size = 12, sort = "productId", direction = Sort.Direction.DESC) Pageable pageable) {
        return productService.Prolistpage(pageable);
    }

    @GetMapping("/list/{catId}")
    public Page<proImageDTO> Prolistpage(@PageableDefault(size = 12, sort = "productId", direction = Sort.Direction.DESC) Pageable pageable,
                                         @PathVariable long catId) {
        return productService.CatProlistpage(catId, pageable);
    }


    /*
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

     */

    @GetMapping("/page/{productId}")
    public ProductGetDTO getProductById(@PathVariable long productId) {
        return productService.ProductInfo(productId);
    }
    //상품 상세 페이지 URL

    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(@PathVariable long productId) {
        productService.Productdelete(productId);
    }
    //재품 삭제 URL
}