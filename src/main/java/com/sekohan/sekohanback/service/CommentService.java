package com.sekohan.sekohanback.service;

import com.sekohan.sekohanback.dto.CommentGetDTO;
import com.sekohan.sekohanback.entity.CommentEntity;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;

import java.util.List;

public interface CommentService {
    CommentEntity uploadComment(String content, ProductEntity productEntity, UserEntity userEntity);
    List<CommentGetDTO> getCommentsByProductId(long productId);
    void deleteComment(long CommentId);
}