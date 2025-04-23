package com.A4Team.GamesShop.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "game")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

    @Column(name = "background", columnDefinition = "TEXT")
    private String background;

    @Column(name = "trailer", columnDefinition = "TEXT")
    private String trailer;

    @Column(name = "is_disable")
    private Boolean isDisable;
}
