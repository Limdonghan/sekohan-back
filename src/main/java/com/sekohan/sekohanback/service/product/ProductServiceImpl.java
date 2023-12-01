package com.sekohan.sekohanback.service.product;

import com.sekohan.sekohanback.dto.ProductGetDTO;
import com.sekohan.sekohanback.dto.proImageDTO;
import com.sekohan.sekohanback.entity.CommentEntity;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.ServiceEntity;
import com.sekohan.sekohanback.entity.WishListEntity;
import com.sekohan.sekohanback.entity.img.ProImageEntity;
import com.sekohan.sekohanback.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j  //log.info("sdfsdf")  //log.info
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Value("${com.sekohan.upload.path}")
    String uploadDir;
    private final ProductRepository productRepository;
    private final ProImageRepository proImageRepository;
    private final WishListrepository wishListrepository;
    private final CommentRepository commentRepository;
    private final SupportRepository supportRepository;

    public Page<proImageDTO> Prolistpage(Pageable pageable) {
        Page<ProductEntity> products = productRepository.findAll(pageable);

        return products.map(this::convertToDTO);
    }

    public Page<proImageDTO> CatProlistpage(long catId, Pageable pageable) {
        Page<ProductEntity> products = productRepository.findByCategoryList(pageable, catId);

        return products.map(this::convertToDTO);
    }

    @Override
    public Page<proImageDTO> Searchpage(String searchvalues, Pageable pageable) {
        Page<ProductEntity> products = productRepository.findByProNameContainingOrProInfoContaining(pageable, searchvalues, searchvalues);
        return products.map(this::convertToDTO);
    }

    /*@Override
    public Page<proImageDTO> addSearchpage(String searchvalues, Pageable pageable) {
        Page<ProductEntity> products = productRepository.
                findByMainInfoContainingOrSubInfoContainingOrDetailInfoContaining(pageable, searchvalues,searchvalues,searchvalues);
        return products.map(this::convertToDTO);
    }*/

    private proImageDTO convertToDTO(ProductEntity product) {
        proImageDTO proImageDTO = com.sekohan.sekohanback.dto.proImageDTO.builder().
                productId(product.getProductId()).
                catId(product.getCategoryEntity().getCatId()).
                proName(product.getProName()).
                proPrice(product.getProPrice()).
                proInfo(product.getProInfo()).
                created_date(product.getLocalDateTime()).
                status(product.getProStatus()).build();

        List<ProImageEntity> proImageEntities = proImageRepository.findproid(product.getProductId());
        if (!proImageEntities.isEmpty()) {
            ProImageEntity proImageEntity = proImageEntities.get(0); // 가장 오래된 항목 선택
            proImageDTO.setPath(proImageEntity.getPath());
        }

        return proImageDTO;
    }

    public ProductGetDTO ProductInfo(long productId) {
        productRepository.viewup(productId);
        return productRepository.findById(productId)
                .map(this::ProgetDTO)
                .orElse(null); // or throw an exception if required
    }

    private ProductGetDTO ProgetDTO(ProductEntity product) {
        LocalDateTime currentDateTime = product.getLocalDateTime();
        String submittime = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH시mm분"));
        ProductGetDTO productGetDTO = ProductGetDTO.builder().
                productId(product.getProductId()).
                proName(product.getProName()).
                proInfo(product.getProInfo()).
                proPrice(product.getProPrice()).
                proView(product.getProView()).
                created_time(submittime).
                proStatus(product.getProStatus()).
                catId(product.getCategoryEntity().getCatId()).
                DetailName(product.getCategoryEntity().getCatDetailClass()).
                uId(product.getUserEntity().getUId()).
                nickName(product.getUserEntity().getNickname()).
                build();

        List<String> fileAddresses = new ArrayList<>(); // 파일 주소를 저장할 리스트
        List<ProImageEntity> images = proImageRepository.getPro_imgId(product.getProductId());
        for (ProImageEntity image : images) {
            fileAddresses.add(image.getPath());
        }
        productGetDTO.setPath(fileAddresses);
        return productGetDTO;
    }
    //상품 상세정보 코드

    public void Productdelete(long productId) {
        List<ProImageEntity> existingImages = proImageRepository.getPro_imgId(productId);
        for (ProImageEntity existingImage : existingImages) {
            proImageRepository.delete(existingImage);
            String imagePath = existingImage.getPath();
            String fullImagePath = uploadDir + File.separator + imagePath;

            File imageFile = new File(fullImagePath);
            //저장되어있는 이미지파일 삭제
            if (imageFile.exists()) {
                boolean deleted = imageFile.delete();
            }
            List<WishListEntity> wishdelete = wishListrepository.getproId(productId);
            for (WishListEntity wishdelet : wishdelete) {
                wishListrepository.delete(wishdelet);
            }
            List<CommentEntity> commentDelete = commentRepository.getproId(productId);
            for (CommentEntity commentDelet : commentDelete) {
                commentRepository.delete(commentDelet);
            }
            List<ServiceEntity> reportDelete = supportRepository.getproid(productId);
            for (ServiceEntity serviceEntity : reportDelete)
                supportRepository.delete(serviceEntity);
            //상품 지우기전에 상품 productid를 참고하는 모든테이블의 값 삭제.
            productRepository.deleteById(productId);
        }
    }

}