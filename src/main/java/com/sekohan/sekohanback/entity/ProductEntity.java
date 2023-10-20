package com.sekohan.sekohanback.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_Product")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_ATTR_REPLY")// 11번 12번줄 있으면 기본키가 1부터 시작해서 1씩 자동으로 올라감
    @SequenceGenerator(name = "SEQ_ATTR_REPLY", sequenceName = "SEQUENCE_ATTR_REPLY", allocationSize = 1) // 인덱스값 다른 조합으로 하고 싶으면 지우셈
    private long pro_id;

    @Column(nullable = false)
    private String pro_name;

    @Column(nullable = false)
    private int pro_price;

    @Column(nullable = false)
    private String pro_info;

    @Column(nullable = false)
    private LocalDateTime localDateTime;  //생성일자

    @ColumnDefault("0")  //default 0
    @Column(nullable = false)
    private int pro_view;

    @ColumnDefault("0")  //default 0
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private byte pro_status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user_id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category_id;

}
