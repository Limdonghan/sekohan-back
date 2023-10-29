package com.sekohan.sekohanback.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_Category")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_CATEGORY")// 11번 12번줄 있으면 기본키가 1부터 시작해서 1씩 자동으로 올라감
    @SequenceGenerator(name = "SEQ_CATEGORY", sequenceName = "SEQUENCE_CATEGORY", allocationSize = 1) // 인덱스값 다른 조합으로 하고 싶으면 지우셈
    private long catId;

    @Column(nullable = false)
    private String groupId;

    @Column(nullable = false)
    private int catLev;

    @Column(nullable = false)
    private String catClass;

    @Column(nullable = false)
    private int catDetailLev;

    @Column(nullable = false)
    private String catDetailClass;

    @Column(nullable = false)
    private int catParentLev;

    @Column(nullable = false)
    private int catDetailParentLev;

}
