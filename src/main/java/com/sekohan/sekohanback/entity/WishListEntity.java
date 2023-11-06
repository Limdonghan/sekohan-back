package com.sekohan.sekohanback.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_WishList")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public class WishListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_WISH")
// 11번 12번줄 있으면 기본키가 1부터 시작해서 1씩 자동으로 올라감
    @SequenceGenerator(name = "SEQ_WISH", sequenceName = "SEQUENCE_WISH", allocationSize = 1)
    // 인덱스값 다른 조합으로 하고 싶으면 지우셈
    private long wishListId;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime localDateTime;  //생성일자

    @ManyToOne
    @JoinColumn(name = "uId")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductEntity productEntity;
}
