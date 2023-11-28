package com.sekohan.sekohanback.service.comment;

import com.sekohan.sekohanback.dto.CommentGetDTO;
import com.sekohan.sekohanback.entity.CommentEntity;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;

import java.util.List;

public interface CommentService {
    CommentEntity CommentAdd(String content, ProductEntity productEntity, UserEntity userEntity);
    List<CommentGetDTO> CommentList(long productId);
    void Commentdelete(long CommentId);
}