package com.A4Team.GamesShop.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchase_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "payment_id", nullable = false)
    private Integer paymentId; // Liên kết với bảng payment

    @Column(name = "game_key", nullable = false, length = 50, unique = true)
    private String gameKey; // Khóa game, tự tạo theo định dạng 'AAA-22L-GGG_TTT'

    @Column(name = "activated", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean activated = false; // Mặc định là false

    // @Column(name = "purchase_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    // private LocalDateTime purchaseDate; // Thời gian mua hàng, tự động tạo trong DB

    @Column(name = "download_url", nullable = false, length = 255)
    private String downloadUrl; // Địa chỉ tải về

    @Column(name = "reward_point", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer rewardPoint = 0; // Điểm thưởng mặc định là 0

    // Quan hệ với bảng Payment (foreign key)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Purchase payment; // Để ánh xạ thông tin từ bảng payment nếu cần

}
