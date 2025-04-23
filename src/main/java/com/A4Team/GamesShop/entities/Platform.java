package com.A4Team.GamesShop.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "platform")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
}
