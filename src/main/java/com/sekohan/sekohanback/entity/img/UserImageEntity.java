package com.sekohan.sekohanback.entity.img;

import com.sekohan.sekohanback.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_User_Img")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class UserImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_USER_IMG")// 11번 12번줄 있으면 기본키가 1부터 시작해서 1씩 자동으로 올라감
    @SequenceGenerator(name = "SEQ_USER_IMG", sequenceName = "SEQUENCE_USER_IMG", allocationSize = 1) // 인덱스값 다른 조합으로 하고 싶으면 지우셈
    private long uImgId;

    @Column(nullable = true)
    private String path;

    @OneToOne(mappedBy = "userImage")
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public void updateUrl(String path) {
        this.path = path;
    }
}
