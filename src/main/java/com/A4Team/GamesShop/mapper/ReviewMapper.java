package com.A4Team.GamesShop.mapper;

import com.A4Team.GamesShop.dto.UserShortDTO;
import com.A4Team.GamesShop.dto.GameShortDTO;
import com.A4Team.GamesShop.dto.ReviewDTO;
import com.A4Team.GamesShop.entities.Review;

public class ReviewMapper {
    public static ReviewDTO toDTO(Review review) {
        UserShortDTO userDTO = new UserShortDTO(
                review.getUser().getId(),
                review.getUser().getNickName(),
                review.getUser().getAvatar());

        GameShortDTO gameDTO = new GameShortDTO(
                review.getGame().getId(),
                review.getGame().getName(),
                review.getGame().getImage());

        return new ReviewDTO(
                review.getId(),             // 1. id
                review.getGameId(),         // 2. gameId
                review.getStatus(),         // 3. status
                review.getComment(),        // 4. comment
                review.getUseFul(),
                review.getCreatedAt(),      // 5. createdAt
                userDTO,                    // 6. user
                gameDTO                     // 7. game
        );
    }
}
