package com.sekohan.sekohanback.service.myproduct;

import com.sekohan.sekohanback.dto.proImageDTO;
import com.sekohan.sekohanback.entity.CategoryEntity;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.entity.img.ProImageEntity;
import com.sekohan.sekohanback.repository.ProImageRepository;
import com.sekohan.sekohanback.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${com.sekohan.upload.path}")
    String uploadDir;
    private final ProImageRepository proImageRepository;
    private final ProductRepository productRepository;


    @Override
    public List<proImageDTO> UserProList(long uId) {
        List<ProImageEntity> images = proImageRepository.findByuId(uId);
        List<proImageDTO> result = new ArrayList<>();
        List<Long> seenProductIds = new ArrayList<>();
        for (ProImageEntity image : images) {
            int proStatus = image.getProductEntity().getProStatus();
            if (proStatus == 0 || proStatus == 1) {
                if (!seenProductIds.contains(image.getProductEntity().getProductId())) {
                    seenProductIds.add(image.getProductEntity().getProductId());
                    result.add(convertToDTO(image));
                }
            }
        }

        return result;
    }

    @Override
    public List<proImageDTO> UserProSoldoutList(long uId) {
        List<ProImageEntity> images = proImageRepository.findByuId(uId);
        List<proImageDTO> result = new ArrayList<>();
        List<Long> seenProductIds = new ArrayList<>();
        for (ProImageEntity image : images) {
            if (image.getProductEntity().getProStatus() == 2){
                if (!seenProductIds.contains(image.getProductEntity().getProductId())) {
                    seenProductIds.add(image.getProductEntity().getProductId());
                    result.add(convertToDTO(image));
                }
            }
        }

        return result;
    }


    @Override
    public ProductEntity updateProduct(long productId, String proName, int proPrice, String proInfo, UserEntity userEntity, CategoryEntity categoryEntity, byte status, List<MultipartFile> files) {
        List<ProImageEntity> existingImages = proImageRepository.getPro_imgId(productId);
        for (ProImageEntity existingImage : existingImages) {
            String existingImagePath = existingImage.getPath();
            deleteImageFile(existingImagePath);
            //파일업데이트하기전 이미저장되어있는 이미지파일 삭제
            proImageRepository.delete(existingImage);
        }

        ProductEntity productEntity = ProductEntity.builder().userEntity(userEntity).
                productId(productId).proName(proName).proPrice(proPrice).
                proInfo(proInfo).localDateTime(LocalDateTime.now()).proView(0)
                .proStatus((byte) 0).categoryEntity(categoryEntity).proStatus(status).
                build();

        ProductEntity savedProduct = productRepository.save(productEntity);

        for (MultipartFile imagePath : files) {
            ProImageEntity proImageEntity = ProImageEntity.builder().path(imagePath.getOriginalFilename()).
                    productEntity(savedProduct).build();
        }
        int i = 0;
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    String originalFilename = file.getOriginalFilename();
                    String cleanedFilename = originalFilename.replaceAll("[^a-zA-Z0-9.-]", "_");
                    //특정문자 _ 로 치환하기
                    String formattedDateTime = LocalDateTime.now().
                            format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
                    //날짜 포멧 형식
                    String newFileName = formattedDateTime + "_" + i + "_" + cleanedFilename;
                    //중복 이름 방지를 위하여 변수 추가 저장시간+변수+파일이름 으로 저장
                    ProImageEntity proImageEntity = ProImageEntity.builder().
                            path(newFileName).productEntity(savedProduct).build();
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
        //파일업데이트하기전 이미저장되어있는 이미지파일 삭제
    }

    private proImageDTO convertToDTO(ProImageEntity image) {
        proImageDTO proImageDTO = new proImageDTO();
        proImageDTO.setProductId(image.getProductEntity().getProductId());
        proImageDTO.setCatId(image.getProductEntity().getCategoryEntity().getCatId());
        proImageDTO.setProName(image.getProductEntity().getProName());
        proImageDTO.setProPrice(image.getProductEntity().getProPrice());
        proImageDTO.setProInfo(image.getProductEntity().getProInfo().substring(0, Math.min(image.getProductEntity().getProInfo().length(), 10)));
        proImageDTO.setPath(image.getPath());
        proImageDTO.setCreated_date(image.getProductEntity().getLocalDateTime());
        proImageDTO.setStatus(image.getProductEntity().getProStatus());
        return proImageDTO;
    }
}
