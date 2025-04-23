package com.A4Team.GamesShop.mapper;

import com.A4Team.GamesShop.dto.CommentDTO;
import com.A4Team.GamesShop.dto.UserShortDTO;
import com.A4Team.GamesShop.dto.GameShortDTO;
import com.A4Team.GamesShop.entities.Comment;

public class CommentMapper {
    public static CommentDTO toDTO(Comment comment) {
        UserShortDTO userDTO = new UserShortDTO(
                comment.getUser().getId(),
                comment.getUser().getNickName(),
                comment.getUser().getAvatar());

        GameShortDTO gameDTO = new GameShortDTO(
                comment.getGame().getId(),
                comment.getGame().getName(),
                comment.getGame().getImage());

                return new CommentDTO(
                    comment.getId(),            // 1. id
                    comment.getGameId(),        // 2. gameId
                    comment.getRating(),        // 3. rating
                    comment.getComment(),       // 4. comment
                    comment.getShareCount(),    // 5. shareCount
                    comment.getCreatedAt(),     // 6. createdAt
                    userDTO,                    // 7. user
                    gameDTO                     // 8. game
                );
    }   
}