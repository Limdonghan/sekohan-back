package com.sekohan.sekohanback.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "TB_User")
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Getter
@ToString
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_USER")// 11번 12번줄 있으면 기본키가 1부터 시작해서 1씩 자동으로 올라감
    @SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQUENCE_USER", allocationSize = 1) // 인덱스값 다른 조합으로 하고 싶으면 지우셈
    private long uId;

    @Column(unique = true)  //NotNull
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String nickname;

    @ColumnDefault("0")  //default 0
    @Column(nullable = false)
    private int report;

    @OneToOne
    @JoinColumn(name = "userImageID")
    private UserImageEntity userImage;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<UserRole> roleSet = new HashSet<>();

}
