package com.sekohan.sekohanback.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_Event")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_ATTR_REPLY")// 11번 12번줄 있으면 기본키가 1부터 시작해서 1씩 자동으로 올라감
    @SequenceGenerator(name = "SEQ_ATTR_REPLY", sequenceName = "SEQUENCE_ATTR_REPLY", allocationSize = 1) // 인덱스값 다른 조합으로 하고 싶으면 지우셈
    private long address_id;

    @Column(nullable = false)
    private String main_add;

    @Column(nullable = false)
    private String sub_add;

    @Column(nullable = false)
    private String detail_add;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user_id;
}
