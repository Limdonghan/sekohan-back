package com.sekohan.sekohanback.entity.admin;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_Event_Image")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
public class EventImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EVENT_IMAGE")
    @SequenceGenerator(name = "SEQ_EVENT_IMAGE", sequenceName = "SEQUENCE_EVENT_IMAGE", allocationSize = 1)
    private Long eventImgId;

    
    private String uuid;
    private String originalFileName;  //기존 파일 이름
    private String path;  //파일 경로

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evnetID")
    private EventEntity eventEntity;
}
