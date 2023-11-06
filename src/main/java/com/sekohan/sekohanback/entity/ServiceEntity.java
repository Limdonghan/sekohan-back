package com.sekohan.sekohanback.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_Service")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_SERVICE")// 11번 12번줄 있으면 기본키가 1부터 시작해서 1씩 자동으로 올라감
    @SequenceGenerator(name = "SEQ_SERVICE", sequenceName = "SEQUENCE_SERVICE", allocationSize = 1) // 인덱스값 다른 조합으로 하고 싶으면 지우셈
    private long serviceId;

    @ManyToOne
    @JoinColumn(name = "uId")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductEntity productEntity;

}
