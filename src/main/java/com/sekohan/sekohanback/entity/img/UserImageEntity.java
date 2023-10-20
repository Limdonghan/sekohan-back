package com.sekohan.sekohanback.entity.img;

import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.mapping.ToOne;

@Entity
@Table(name = "TB_User_Img")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class UserImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_ATTR_REPLY")// 11번 12번줄 있으면 기본키가 1부터 시작해서 1씩 자동으로 올라감
    @SequenceGenerator(name = "SEQ_ATTR_REPLY", sequenceName = "SEQUENCE_ATTR_REPLY", allocationSize = 1) // 인덱스값 다른 조합으로 하고 싶으면 지우셈
    private long user_img_id;

    @Column(nullable = true)
    private String path;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user_id;
}
