package com.sekohan.sekohanback.entity.img;

import com.sekohan.sekohanback.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_Pro_Img")
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Getter
@Setter
public class ProImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_ATTR_REPLY_PROIMAGE")// 11번 12번줄 있으면 기본키가 1부터 시작해서 1씩 자동으로 올라감
    @SequenceGenerator(name = "SEQ_ATTR_REPLY_PROIMAGE", sequenceName = "SEQUENCE_ATTR_REPLY_PROIMAGE", allocationSize = 1) // 인덱스값 다른 조합으로 하고 싶으면 지우셈
    private long proImgId;

    @Column(nullable = true)
    private String path;

    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductEntity productId;


}
