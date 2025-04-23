package com.A4Team.GamesShop.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game_with_prices")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameWithPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "game_id")
    private Integer gameId;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

    @Column(name = "background", columnDefinition = "TEXT")
    private String background;

    @Column(name = "original_price")
    private Double original_price;

    @Column(name = "discount_percent")
    private Integer discount_percent;

    @Column(name = "final_price")
    private Double final_price;
}
