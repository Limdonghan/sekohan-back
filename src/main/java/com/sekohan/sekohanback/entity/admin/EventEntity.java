package com.sekohan.sekohanback.entity.admin;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_Event")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter

public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_EVENT")// 11번 12번줄 있으면 기본키가 1부터 시작해서 1씩 자동으로 올라감
    @SequenceGenerator(name = "SEQ_EVENT", sequenceName = "SEQUENCE_EVENT", allocationSize = 1) // 인덱스값 다른 조합으로 하고 싶으면 지우셈
    private long eid;

    @Column(nullable = false)
    private String name;  //이벤트 이름

    @CreatedDate
    @Column(nullable = false,updatable = false)
    private LocalDateTime localDateTime;  //생성일자

    @OneToOne(mappedBy = "eventEntity",fetch = FetchType.LAZY)
    private EventImageEntity eventImageEntity;

}
