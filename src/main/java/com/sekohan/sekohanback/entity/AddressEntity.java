package com.sekohan.sekohanback.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_Address")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_ATTR_REPLY")// 11번 12번줄 있으면 기본키가 1부터 시작해서 1씩 자동으로 올라감
    @SequenceGenerator(name = "SEQ_ATTR_REPLY", sequenceName = "SEQUENCE_ATTR_REPLY", allocationSize = 1) // 인덱스값 다른 조합으로 하고 싶으면 지우셈
    private long addressId;

    @Column(nullable = false)
    private String mainInfo;

    @Column(nullable = false)
    private String subInfo;

    @Column(nullable = false)
    private String detailInfo;

    @ManyToOne
    @JoinColumn(name = "uId")
    private UserEntity uId;
}
