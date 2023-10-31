package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.ProductDTO;
import com.sekohan.sekohanback.dto.ProductGetDTO;
import com.sekohan.sekohanback.dto.proImageDTO;
import com.sekohan.sekohanback.entity.CategoryEntity;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/upload")
    public ProductEntity uploadProduct(@RequestParam("proName") String proName,
                                       @RequestParam("proPrice") int proPrice,
                                       @RequestParam("proInfo") String proInfo,
                                       @RequestParam("categoryId") long categoryId,
                                       @RequestParam("userId") long userId,
                                       @RequestPart("files") List<MultipartFile> files) {
        CategoryEntity categoryEntity = new CategoryEntity(categoryId);
        categoryEntity.setCatId(categoryId);
        UserEntity userEntity = new UserEntity(userId);
        userEntity.setUId(userId);
        return productService.uploadProduct(proName, proPrice, proInfo, categoryEntity, userEntity, files);
    }


    @GetMapping("/list")
    public List<proImageDTO> getAllList(){
        return productService.listProduct();
    }

    @GetMapping("/list/{catId}")
    public List<proImageDTO> getcatList(@PathVariable long catId){
        return productService.getcatList(catId);
    }

    @GetMapping("/page/{productId}")
    public ProductGetDTO getProductById(@PathVariable long productId) {
        return productService.getProductById(productId);
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(@PathVariable long productId) {
        productService.deleteProduct(productId);
    }
}