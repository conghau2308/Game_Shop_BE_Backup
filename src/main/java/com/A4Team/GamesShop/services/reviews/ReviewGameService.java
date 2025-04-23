package com.A4Team.GamesShop.services.reviews;

import com.A4Team.GamesShop.entities.ReviewGame;
import com.A4Team.GamesShop.model.request.ReviewRequest;
import com.A4Team.GamesShop.repository.ReviewGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewGameService {

    @Autowired
    private ReviewGameRepository reviewGameRepository;

    public void create(ReviewRequest request) {
        ReviewGame review = new ReviewGame();
        review.setUserId(request.getUserId());
        review.setGameId(request.getGameId());
        // review.setCreatedAt(request.getCreatedAt());
        review.setStatus(request.getStatus());
        review.setUseFul(request.getUseFul());
        review.setComment(request.getComment());

        reviewGameRepository.save(review);
    }
}
