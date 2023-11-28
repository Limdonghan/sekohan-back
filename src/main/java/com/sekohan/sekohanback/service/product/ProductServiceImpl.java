package com.sekohan.sekohanback.service.product;

import com.sekohan.sekohanback.dto.ProductGetDTO;
import com.sekohan.sekohanback.dto.proImageDTO;
import com.sekohan.sekohanback.entity.CommentEntity;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.WishListEntity;
import com.sekohan.sekohanback.entity.img.ProImageEntity;
import com.sekohan.sekohanback.repository.CommentRepository;
import com.sekohan.sekohanback.repository.ProImageRepository;
import com.sekohan.sekohanback.repository.ProductRepository;
import com.sekohan.sekohanback.repository.WishListrepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Page<proImageDTO> Prolistpage(Pageable pageable) {
        Page<ProductEntity> products = productRepository.findAll(pageable);

        return products.map(this::convertToDTO);
    }

    public Page<proImageDTO> CatProlistpage(long catId, Pageable pageable) {
        Page<ProductEntity> products = productRepository.findByCategoryList(pageable, catId);

        return products.map(this::convertToDTO);
    }

    private proImageDTO convertToDTO(ProductEntity product) {
        proImageDTO proImageDTO = new proImageDTO();
        proImageDTO.setProductId(product.getProductId());
        proImageDTO.setCatId(product.getCategoryEntity().getCatId());
        proImageDTO.setProName(product.getProName());
        proImageDTO.setProPrice(product.getProPrice());
        proImageDTO.setProInfo(product.getProInfo());
        proImageDTO.setCreated_date(product.getLocalDateTime());
        proImageDTO.setStatus(product.getProStatus());

        List<ProImageEntity> proImageEntities = proImageRepository.findproid(product.getProductId());
        if (!proImageEntities.isEmpty()) {
            ProImageEntity proImageEntity = proImageEntities.get(0); // 가장 오래된 항목 선택
            proImageDTO.setPath(proImageEntity.getPath());
        }

        return proImageDTO;
    }
/*
    public List<proImageDTO> Prolist() {
        List<ProImageEntity> images = proImageRepository.findAll();
        List<proImageDTO> result = new ArrayList<>();
        List<Long> seenProductIds = new ArrayList<>();

        for (ProImageEntity image : images) {
            if (!seenProductIds.contains(image.getProductEntity().getProductId())) {
                seenProductIds.add(image.getProductEntity().getProductId());
                result.add(convertToDTO(image));
            }
        }

        return result;
    }

    public List<proImageDTO> CatProList(long catId) {
        List<ProImageEntity> images = proImageRepository.findByCategoryList(catId);
        List<proImageDTO> result = new ArrayList<>();
        List<Long> seenProductIds = new ArrayList<>();

        for (ProImageEntity image : images) {
            if (!seenProductIds.contains(image.getProductEntity().getProductId())) {
                seenProductIds.add(image.getProductEntity().getProductId());
                result.add(convertToDTO(image));
            }
        }

        return result;
    }
    //특정 catId 값만 출력하는 상품 리스트 서비스

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
    //상품 리스트 코드
*/
    public ProductGetDTO ProductInfo(long productId) {
        productRepository.viewup(productId);
        return productRepository.findById(productId)
                .map(this::ProgetDTO)
                .orElse(null); // or throw an exception if required
    }

    private ProductGetDTO ProgetDTO(ProductEntity product){
        ProductGetDTO productGetDTO = new ProductGetDTO();
        LocalDateTime currentDateTime = product.getLocalDateTime();
        String submittime = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH시mm분"));
        productGetDTO.setProductId(product.getProductId());
        productGetDTO.setProName(product.getProName());
        productGetDTO.setProInfo(product.getProInfo());
        productGetDTO.setProPrice(product.getProPrice());
        productGetDTO.setProView(product.getProView());
        productGetDTO.setCreated_time(submittime);
        productGetDTO.setProStatus(product.getProStatus());
        productGetDTO.setCatId(product.getCategoryEntity().getCatId());
        productGetDTO.setDetailName(product.getCategoryEntity().getCatDetailClass());
        productGetDTO.setUId(product.getUserEntity().getUId());
        productGetDTO.setNickName(product.getUserEntity().getNickname());

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
            String existingImagePath = existingImage.getPath();
            proImageRepository.delete(existingImage);
        }
        List<WishListEntity> wishdelete = wishListrepository.getproId(productId);
        for (WishListEntity wishdelet : wishdelete){
            long wish = wishdelet.getProductEntity().getProductId();
            wishListrepository.delete(wishdelet);
        }
        List<CommentEntity> commentDelete = commentRepository.getproId(productId);
        for (CommentEntity commentDelet : commentDelete){
            long comt = commentDelet.getProductEntity().getProductId();
            commentRepository.delete(commentDelet);
        }
        //상품 지우기전에 상품 productid를 참고하는 모든테이블의 값 삭제.
            productRepository.deleteById(productId);
    }

}