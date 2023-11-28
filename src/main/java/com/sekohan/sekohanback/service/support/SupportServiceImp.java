package com.sekohan.sekohanback.service.support;

import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.ServiceEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.repository.SupportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@Slf4j  //log.info("sdfsdf")  //log.info
@RequiredArgsConstructor
public class SupportServiceImp implements SupportService{
    
    private final SupportRepository supportRepository;
    
    @Override
    public void userSearching(Long uId) {

    }

    @Override
    public String productreport(ProductEntity productEntity, UserEntity userEntity) {
        if (supportRepository.findByProductEntityAndUserEntity(productEntity, userEntity).isPresent()) {
            log.info("중복값");
            return "중복값";
        }
        try {
            ServiceEntity serviceEntity = ServiceEntity.builder()
                    .userEntity(userEntity)
                    .productEntity(productEntity)
                    .build();
            supportRepository.reportup(userEntity.getUId());
            ServiceEntity savedEntity = supportRepository.save(serviceEntity);
            return (savedEntity != null) ? "성공" : "실패";
        } catch (DataIntegrityViolationException e) {
            log.info("중복값");
            return "중복값";
        }
    }
}
