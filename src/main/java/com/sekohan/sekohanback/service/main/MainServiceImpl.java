package com.sekohan.sekohanback.service.main;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.sekohan.sekohanback.dto.page.PageRequestDTO;
import com.sekohan.sekohanback.dto.page.PageResultDTO;
import com.sekohan.sekohanback.dto.product.ProductMainPageDTO;
import com.sekohan.sekohanback.dto.product.ProductSearchDTO;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static com.sekohan.sekohanback.entity.QProductEntity.productEntity;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainServiceImpl implements MainService{
    public final ProductRepository productRepository;


    @Override
    public PageResultDTO<ProductMainPageDTO, ProductEntity> viewList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("proView").descending());
        Page<ProductEntity> page = productRepository.findAll(pageable);
        Function<ProductEntity, ProductMainPageDTO> fn = productEntity -> entityToDTO(productEntity);
        return new PageResultDTO<>(page,fn);
    }

    @Override
    public PageResultDTO<ProductMainPageDTO, ProductEntity> dateList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("localDateTime").descending());
        Page<ProductEntity> page = productRepository.findAll(pageable);
        Function<ProductEntity, ProductMainPageDTO> fn = productEntity -> entityToDTO(productEntity);
        return new PageResultDTO<>(page,fn);
    }

    @Override
    public PageResultDTO<ProductSearchDTO, ProductEntity> search(PageRequestDTO pageRequestDTO){
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("productId").descending());
        BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);
        Page<ProductEntity> page = productRepository.findAll(booleanBuilder,pageable);
        Function<ProductEntity, ProductSearchDTO> fn = productEntity -> entityToDTOS(productEntity);
        return new PageResultDTO<>(page,fn);
    }

    public BooleanBuilder getSearch(PageRequestDTO requestDTO){
        String type = requestDTO.getType();
        String keyword = requestDTO.getKeyword();
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression = productEntity.productId.gt(1);
        builder.and(expression);
        if(type == null || type.trim().length() == 0){
            return builder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if (type.contains("name")){
            conditionBuilder.or(productEntity.proName.contains(keyword));
        }
        return builder.and(conditionBuilder);
    }
}
