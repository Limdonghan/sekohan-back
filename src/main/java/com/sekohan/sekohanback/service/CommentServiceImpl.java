package com.sekohan.sekohanback.service;

import com.sekohan.sekohanback.dto.CommentGetDTO;
import com.sekohan.sekohanback.entity.CommentEntity;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public CommentEntity uploadComment(String content, ProductEntity productEntity, UserEntity userEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(content);
        commentEntity.setLocalDateTime(LocalDateTime.now());
        commentEntity.setUId(userEntity);
        commentEntity.setProductId(productEntity);

        CommentEntity savedComment = commentRepository.save(commentEntity);

        return savedComment;
    }

    @Override
    public List<CommentGetDTO> getCommentsByProductId(long productId) {
        List<CommentEntity> comments = commentRepository.findByProductId(productId);
        return comments.stream()
                .map(this::comgetDTO)
                .collect(Collectors.toList());
    }

    public CommentGetDTO comgetDTO(CommentEntity comment){
        CommentGetDTO commentGetDTO = new CommentGetDTO();
        commentGetDTO.setCommentId(comment.getCommentId());
        commentGetDTO.setContent(comment.getContent());
        commentGetDTO.setLocalDateTime(comment.getLocalDateTime());
        commentGetDTO.setUID(comment.getUId().getUId());
        commentGetDTO.setUsername(comment.getUId().getNickname());
        return commentGetDTO;
    }

    @Override
    public void deleteComment(long CommentId) {
        commentRepository.deleteById(CommentId);
    }
}