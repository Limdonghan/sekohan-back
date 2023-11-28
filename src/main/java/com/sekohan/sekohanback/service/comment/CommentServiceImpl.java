package com.sekohan.sekohanback.service.comment;

import com.sekohan.sekohanback.dto.CommentGetDTO;
import com.sekohan.sekohanback.entity.CommentEntity;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Override
    public CommentEntity CommentAdd(String content, ProductEntity productEntity, UserEntity userEntity) {

        CommentEntity build = CommentEntity.builder()
                .content(content).localDateTime(LocalDateTime.now()).uId(userEntity).productEntity(productEntity).build();

        CommentEntity savedComment = commentRepository.save(build);

        return savedComment;
    }

    @Override
    public List<CommentGetDTO> CommentList(long productId) {
        List<CommentEntity> comments = commentRepository.findByProductId(productId);
        return comments.stream()
                .map(this::comgetDTO)
                .collect(Collectors.toList());
    }

    public CommentGetDTO comgetDTO(CommentEntity comment){
        CommentGetDTO commentGetDTO = new CommentGetDTO();
        LocalDateTime currentDateTime = comment.getLocalDateTime();
        String submittime = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH시mm분"));
        commentGetDTO.setCommentId(comment.getCommentId());
        commentGetDTO.setContent(comment.getContent());
        commentGetDTO.setLocalDateTime(submittime);
        commentGetDTO.setUID(comment.getUId().getUId());
        commentGetDTO.setUsername(comment.getUId().getNickname());
        return commentGetDTO;
    }

    @Override
    public void Commentdelete(long CommentId) {
        commentRepository.deleteById(CommentId);
    }
}