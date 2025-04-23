package com.A4Team.GamesShop.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "purchase_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment paymentId;

    @Column(name = "activated")
    private Boolean activated;

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    @Column(name = "game_key")
    private String gameKey;
}
