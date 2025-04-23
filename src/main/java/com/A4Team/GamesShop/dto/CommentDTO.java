package com.A4Team.GamesShop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentDTO {
    private Integer id;
    private Integer gameId;
    private Integer rating;
    private String comment;
    private Integer shareCount;
    private LocalDateTime createdAt;
    private UserShortDTO user;
    private GameShortDTO game;

    public CommentDTO(Integer id, Integer gameId, Integer rating, String comment,
                      Integer shareCount, LocalDateTime createdAt,
                      UserShortDTO user, GameShortDTO game) {
        this.id = id;
        this.gameId = gameId;
        this.rating = rating;
        this.comment = comment;
        this.shareCount = shareCount;
        this.createdAt = createdAt;
        this.user = user;
        this.game = game;
    }
}
