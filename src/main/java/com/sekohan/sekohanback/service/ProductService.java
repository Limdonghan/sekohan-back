package com.sekohan.sekohanback.service;

import com.sekohan.sekohanback.entity.CategoryEntity;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.entity.img.ProImageEntity;
import com.sekohan.sekohanback.repository.ProductRepository;
import com.sekohan.sekohanback.repository.ProImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private String uploadDir = "src/main/upload/";
    private final ProductRepository productRepository;
    private final ProImageRepository proImageRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProImageRepository proImageRepository) {
        this.productRepository = productRepository;
        this.proImageRepository = proImageRepository;
    }

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductEntity getProductById(long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public ProductEntity uploadProduct(String proName, int proPrice, String proInfo, CategoryEntity categoryEntity, UserEntity userEntity, List<MultipartFile> files) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProName(proName);
        productEntity.setProPrice(proPrice);
        productEntity.setProInfo(proInfo);
        productEntity.setLocalDateTime(LocalDateTime.now());
        productEntity.setProView(0);
        productEntity.setProStatus((byte) 0);
        productEntity.setUId(userEntity);
        productEntity.setCatId(categoryEntity);

        ProductEntity savedProduct = productRepository.save(productEntity);

        for (MultipartFile imagePath : files) {
            ProImageEntity proImageEntity = new ProImageEntity();
            proImageEntity.setPath(imagePath.getOriginalFilename());
            proImageEntity.setProductId(savedProduct);
            proImageRepository.save(proImageEntity);
        }
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    String originalFilename = file.getOriginalFilename();
                    String cleanedFilename = originalFilename.replaceAll("[^a-zA-Z0-9.-]", "_");
                    String newFileName = LocalDateTime.now() + "-" + UUID.randomUUID() + "-" + savedProduct.getProductId() + "-" + cleanedFilename;
                    Path path = Paths.get(uploadDir + newFileName);
                    Files.write(path, bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return savedProduct;
    }

    public void deleteProduct(long productId) {
        productRepository.deleteById(productId);
    }
}