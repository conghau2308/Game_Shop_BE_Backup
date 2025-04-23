package com.A4Team.GamesShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameShortDTO {
    private Integer id;
    private String name;
    private String image;
}
