package com.sekohan.sekohanback.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sekohan.sekohanback.service.ProductService;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_Product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_ATTR_REPLY")// 11번 12번줄 있으면 기본키가 1부터 시작해서 1씩 자동으로 올라감
    @SequenceGenerator(name = "SEQ_ATTR_REPLY", sequenceName = "SEQUENCE_ATTR_REPLY", allocationSize = 1) // 인덱스값 다른 조합으로 하고 싶으면 지우셈
    private long productId;

    @Column(name = "pro_name", nullable = false)
    private String proName;

    @Column(name = "pro_price", nullable = false)
    private int proPrice;

    @Column(name = "pro_info", nullable = false)
    private String proInfo;

    @Column(name = "local_date_time", nullable = false)
    private LocalDateTime localDateTime;

    @ColumnDefault("0")
    @Column(name = "pro_view", nullable = false)
    private int proView;

    @ColumnDefault("0")
    @Column(name = "pro_status", nullable = false, columnDefinition = "CHAR(1)")
    private byte proStatus;

    @ManyToOne
    @JoinColumn(name = "uId")
    private UserEntity uId;

    @ManyToOne
    @JoinColumn(name = "catId")
    private CategoryEntity catId;

}
