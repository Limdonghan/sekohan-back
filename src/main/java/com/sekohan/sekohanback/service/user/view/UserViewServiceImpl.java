package com.sekohan.sekohanback.service.user.view;

import com.sekohan.sekohanback.dto.PageRequestDTO;
import com.sekohan.sekohanback.dto.PageResultDTO;
import com.sekohan.sekohanback.dto.user.UserListDTO;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor  //생성자를 자동으로 생성해주는 어노테이션
@Slf4j
public class UserViewServiceImpl implements UserViewService{
    private final UserRepository userRepository;

    @Override
    public PageResultDTO<UserListDTO, UserEntity> getList(PageRequestDTO pageRequestDTO){
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("uId").descending());

        Page<UserEntity> page = userRepository.findAll(pageable);
        Function<UserEntity, UserListDTO> fn= userEntity -> entityToDTO(userEntity);
        return new PageResultDTO<>(page,fn);
    }
}
