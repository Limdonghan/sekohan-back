package com.sekohan.sekohanback.service.user.view;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.sekohan.sekohanback.dto.page.PageRequestDTO;
import com.sekohan.sekohanback.dto.page.PageResultDTO;
import com.sekohan.sekohanback.dto.user.list.UserListDTO;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static com.sekohan.sekohanback.entity.QUserEntity.userEntity;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserViewServiceImpl implements UserViewService{
    private final UserRepository userRepository;

    @Override
    public PageResultDTO<UserListDTO, UserEntity> getList(PageRequestDTO pageRequestDTO){
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("uId").ascending());
        BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);
        Page<UserEntity> page = userRepository.findAll(booleanBuilder,pageable);
        Function<UserEntity, UserListDTO> fn= userEntity -> entityToDTO(userEntity);
        return new PageResultDTO<>(page,fn);
    }

    public BooleanBuilder getSearch(PageRequestDTO requestDTO){
        String type = requestDTO.getType();
        String keyword = requestDTO.getKeyword();
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression = userEntity.uId.gt(1);
        builder.and(expression);
        if(type == null || type.trim().length() == 0){
            return builder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if(type.contains("login")){
            conditionBuilder.or(userEntity.login.contains(keyword));
        }
        if(type.contains("nickname")){
            conditionBuilder.or(userEntity.nickname.contains(keyword));
        }
        if(type.contains("name")){
            conditionBuilder.or(userEntity.name.contains(keyword));
        }
        return builder.and(conditionBuilder);
    }
}
