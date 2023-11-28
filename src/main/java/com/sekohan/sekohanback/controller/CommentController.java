package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.CommentGetDTO;
import com.sekohan.sekohanback.entity.CommentEntity;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/insert")
    public CommentEntity CommentAdd(@RequestParam("content") String content,
                                       @RequestParam("productId") long productId,
                                       @RequestParam("userId") long userId) {
        ProductEntity productEntity = ProductEntity.builder().productId(productId).build();
        UserEntity userEntity = UserEntity.builder().uId(userId).build();
        return commentService.CommentAdd(content, productEntity, userEntity);
    }
    //댓글 입력 URL

    @GetMapping("/list/{productId}")
    public List<CommentGetDTO> CommentList(@PathVariable long productId) {
        return commentService.CommentList(productId);
    }
    //상품 댓글 리스트 URL

    @DeleteMapping("/delete/{comId}")
    public void deleteProduct(@PathVariable long comId) {
        commentService.Commentdelete(comId);
    }
    //댓글 삭제 URL
}