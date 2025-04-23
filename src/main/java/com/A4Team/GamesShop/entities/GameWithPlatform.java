package com.A4Team.GamesShop.entities;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game_platforms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameWithPlatform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "platform_id")
    private Platform platform;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "is_in_stock")
    private Boolean isInStock;
}
