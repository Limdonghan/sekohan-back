package com.sekohan.sekohanback.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_Product")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_PRODUCT")// 11번 12번줄 있으면 기본키가 1부터 시작해서 1씩 자동으로 올라감
    @SequenceGenerator(name = "SEQ_PRODUCT", sequenceName = "SEQUENCE_PRODUCT", allocationSize = 1) // 인덱스값 다른 조합으로 하고 싶으면 지우셈
    private long productId;

    @Column(nullable = false)
    private String proName;

    @Column(nullable = false)
    private int proPrice;

    @Column(nullable = false)
    private String proInfo;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime localDateTime;  //생성일자

    @ColumnDefault("0")  //default 0
    @Column(nullable = false)
    private int proView;

    @ColumnDefault("0")  //default 0
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private byte proStatus;

    @ManyToOne
    @JoinColumn(name = "uId")
    private UserEntity uId;

    @ManyToOne
    @JoinColumn(name = "catId")
    private CategoryEntity catId;


}
