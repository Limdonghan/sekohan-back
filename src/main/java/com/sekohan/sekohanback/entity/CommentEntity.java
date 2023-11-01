package com.sekohan.sekohanback.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.mapping.ToOne;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_Comment")
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Getter
@Setter
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_ATTR_REPLY_COM")// 11번 12번줄 있으면 기본키가 1부터 시작해서 1씩 자동으로 올라감
    @SequenceGenerator(name = "SEQ_ATTR_REPLY_COM", sequenceName = "SEQUENCE_ATTR_REPLY_COM", allocationSize = 1) // 인덱스값 다른 조합으로 하고 싶으면 지우셈
    private long commentId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime localDateTime;  //생성일자

    @ManyToOne
    @JoinColumn(name = "uId")
    private UserEntity uId;

    @ManyToOne
    @JoinColumn(name="productId")
    private ProductEntity productId;

    public CommentEntity(Long commentId) { this.commentId = commentId; }
}
