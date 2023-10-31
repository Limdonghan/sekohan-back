package com.sekohan.sekohanback.service;

import com.sekohan.sekohanback.dto.proImageDTO;
import com.sekohan.sekohanback.entity.CategoryEntity;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.entity.img.ProImageEntity;
import com.sekohan.sekohanback.repository.ProImageRepository;
import com.sekohan.sekohanback.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
public class MyProductServiceImpl implements MyProductService{

    private String uploadDir = "src/main/upload/";
    private final ProImageRepository proImageRepository;
    private final ProductRepository productRepository;

    @Override
    public List<proImageDTO> getuserproduct(long uId) {
        List<ProImageEntity> images = proImageRepository.findByuId(uId);
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

    @Override
    public ProductEntity updateProduct(long productId, String proName, int proPrice, String proInfo, UserEntity userEntity, CategoryEntity categoryEntity, byte status, List<MultipartFile> files) {
        List<ProImageEntity> existingImages = proImageRepository.getImagesByProductId(productId);
        for (ProImageEntity existingImage : existingImages) {
            String existingImagePath = existingImage.getPath();
            deleteImageFile(existingImagePath);
            proImageRepository.delete(existingImage);
        }

        ProductEntity productEntity = new ProductEntity();
        productEntity.setUId(userEntity);
        productEntity.setProductId(productId);
        productEntity.setProName(proName);
        productEntity.setProPrice(proPrice);
        productEntity.setProInfo(proInfo);
        productEntity.setLocalDateTime(LocalDateTime.now());
        productEntity.setProView(0);
        productEntity.setProStatus((byte) 0);
        productEntity.setCatId(categoryEntity);

        ProductEntity savedProduct = productRepository.save(productEntity);

        for (MultipartFile imagePath : files) {
            ProImageEntity proImageEntity = new ProImageEntity();
            proImageEntity.setPath(imagePath.getOriginalFilename());
            proImageEntity.setProductId(savedProduct);
        }
        int i = 0;
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

    private void deleteImageFile(String imageName) {
        String filePath = uploadDir + imageName;
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("파일 삭제 성공: " + filePath);
            } else {
                System.out.println("파일 삭제 실패: " + filePath);
            }
        } else {
            System.out.println("파일이 존재하지 않습니다: " + filePath);
        }
    }

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
}
