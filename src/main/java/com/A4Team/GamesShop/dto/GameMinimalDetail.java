package com.A4Team.GamesShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameMinimalDetail {
    private Date releaseDate;
    private String gameDescription;
    private String publisherName;
    private String developerName;
    private String genreNames;

    private Integer ratingPoint; // làm tròn phần nguyên từ SQL
    private Long reviews; // tổng số lượng reviews
    private Integer gameId;
}
