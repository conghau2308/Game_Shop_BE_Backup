package com.A4Team.GamesShop.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "purchase_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "payment_id", nullable = false)
    private Integer paymentId;

    @Column(name = "game_key", nullable = false, length = 50)
    private String gameKey;

    @Column(name = "activated", nullable = false)
    private boolean activated = false;

    @Column(name = "download_url", nullable = false, length = 255)
    private String downloadUrl;

    @Column(name = "reward_point", nullable = false)
    private int rewardPoint = 0;

    // @Column(name = "purchase_date", updatable = false)
    // @CreationTimestamp
    // private LocalDateTime purchaseDate;

}
