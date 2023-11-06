package com.sekohan.sekohanback.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_Comment")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_COMMENT")// 11번 12번줄 있으면 기본키가 1부터 시작해서 1씩 자동으로 올라감
    @SequenceGenerator(name = "SEQ_COMMENT", sequenceName = "SEQUENCE_COMMENT", allocationSize = 1) // 인덱스값 다른 조합으로 하고 싶으면 지우셈
    private long commentId;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime localDateTime;  //생성일자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uId")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="productId")
    private ProductEntity productEntity;

}
