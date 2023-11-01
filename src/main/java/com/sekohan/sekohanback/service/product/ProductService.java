package com.sekohan.sekohanback.service.product;

import com.sekohan.sekohanback.dto.ProductGetDTO;
import com.sekohan.sekohanback.dto.proImageDTO;
import com.sekohan.sekohanback.entity.CategoryEntity;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.entity.img.ProImageEntity;
import com.sekohan.sekohanback.repository.ProductRepository;
import com.sekohan.sekohanback.repository.ProImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j  //log.info("sdfsdf")  //log.info
@RequiredArgsConstructor
public class ProductService {

    @Value("${com.sekohan.upload.path}")
    String uploadDir;
    private final ProductRepository productRepository;
    private final ProImageRepository proImageRepository;



/*
    public List<ProductDTO> getAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        log.info("product: {}",products);
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(ProductEntity product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setProName(product.getProName());
        productDTO.setProPrice(product.getProPrice());
        productDTO.setProInfo(product.getProInfo());
        return productDTO;
    }
    //test용 코드
*/

    public List<proImageDTO> listProduct() {
        List<ProImageEntity> images = proImageRepository.findAll();
        List<proImageDTO> result = new ArrayList<>();
        List<Long> seenProductIds = new ArrayList<>();

        for (ProImageEntity image : images) {
            if (!seenProductIds.contains(image.getProductId().getProductId())) {
                seenProductIds.add(image.getProductId().getProductId());
                result.add(convertToDTO(image));
            }
        }

        return result;
    }

    public List<proImageDTO> getcatList(long catId) {
        List<ProImageEntity> images = proImageRepository.findImagesByCategoryId(catId);
        List<proImageDTO> result = new ArrayList<>();
        List<Long> seenProductIds = new ArrayList<>();

        for (ProImageEntity image : images) {
            if (!seenProductIds.contains(image.getProductId().getProductId())) {
                seenProductIds.add(image.getProductId().getProductId());
                result.add(convertToDTO(image));
            }
        }

        return result;
    }
    //특정 catId 값만 출력하는 상품 리스트 서비스

    private proImageDTO convertToDTO(ProImageEntity image) {
        proImageDTO proImageDTO = new proImageDTO();
        proImageDTO.setProductId(image.getProductId().getProductId());
        proImageDTO.setCatId(image.getProductId().getCatId().getCatId());
        proImageDTO.setProName(image.getProductId().getProName());
        proImageDTO.setProPrice(image.getProductId().getProPrice());
        proImageDTO.setProInfo(image.getProductId().getProInfo().substring(0, Math.min(image.getProductId().getProInfo().length(), 10)));
        proImageDTO.setPath(image.getPath());
        proImageDTO.setCreated_date(image.getProductId().getLocalDateTime());
        return proImageDTO;
    }
    //상품 리스트 코드

    public ProductGetDTO getProductById(long productId) {
        return productRepository.findById(productId)
                .map(this::ProgetDTO)
                .orElse(null); // or throw an exception if required
    }

    private ProductGetDTO ProgetDTO(ProductEntity product){
        ProductGetDTO productGetDTO = new ProductGetDTO();
        productGetDTO.setProductId(product.getProductId());
        productGetDTO.setProName(product.getProName());
        productGetDTO.setProInfo(product.getProInfo());
        productGetDTO.setProPrice(product.getProPrice());
        productGetDTO.setProView(product.getProView());
        productGetDTO.setProStatus(product.getProStatus());
        productGetDTO.setCatId(product.getCatId().getCatId());
        productGetDTO.setDetailName(product.getCatId().getCatDetailClass());
        productGetDTO.setUId(product.getUId().getUId());
        productGetDTO.setNickName(product.getUId().getNickname());

        List<String> fileAddresses = new ArrayList<>(); // 파일 주소를 저장할 리스트
        List<ProImageEntity> images = proImageRepository.getImagesByProductId(product.getProductId());
        for (ProImageEntity image : images) {
            fileAddresses.add(image.getPath());
        }
        productGetDTO.setPath(fileAddresses);
        return productGetDTO;
    }
    //상품 상세정보 코드

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
        }
        int i = 0;
        //
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    ProImageEntity proImageEntity = new ProImageEntity();
                    String originalFilename = file.getOriginalFilename();
                    String cleanedFilename = originalFilename.replaceAll("[^a-zA-Z0-9.-]", "_");

                    String formattedDateTime = LocalDateTime.now().
                            format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
                    String newFileName = formattedDateTime + "_" + i + "_" + cleanedFilename;
                    proImageEntity.setPath(newFileName);
                    proImageEntity.setProductId(savedProduct);
                    proImageRepository.save(proImageEntity);
                    i++;
                    Path path = Paths.get(uploadDir + newFileName);
                    Files.write(path, bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return savedProduct;
    }
    //상품 업로드 코드

    public void deleteProduct(long productId) {
        productRepository.deleteById(productId);
    }
    // 상품 지우기 코드
}