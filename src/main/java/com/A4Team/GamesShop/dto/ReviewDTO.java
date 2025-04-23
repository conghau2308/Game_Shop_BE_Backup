package com.A4Team.GamesShop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReviewDTO {
    private Integer id;
    private Integer gameId;
    private String status;
    private String comment;
    private Integer useFul;
    private LocalDateTime createdAt;
    private UserShortDTO user;
    private GameShortDTO game;

    public ReviewDTO(Integer id, Integer gameId, String status, String comment, Integer useFul, LocalDateTime createdAt,
                      UserShortDTO user, GameShortDTO game) {
        this.id = id;
        this.gameId = gameId;
        this.status = status;
        this.comment = comment;
        this.useFul = useFul;
        this.createdAt = createdAt;
        this.user = user;
        this.game = game;
    }
}
