package com.A4Team.GamesShop.controller;

import com.A4Team.GamesShop.dto.ReviewDTO;
import com.A4Team.GamesShop.model.request.ReviewRequest;
import com.A4Team.GamesShop.model.response.BaseResponse;
import com.A4Team.GamesShop.services.reviews.ReviewGameService;
import com.A4Team.GamesShop.services.reviews.ReviewService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewGameService reviewGameService;

    @GetMapping("/all")
    public BaseResponse<List<ReviewDTO>> getAllReviews() {
        List<ReviewDTO> reviews = reviewService.findAllDTO();
        return BaseResponse.success(reviews, "Fetched all reviews successfully");
    }

    @GetMapping("/limit")
    public BaseResponse<List<ReviewDTO>> getLimitedReviews(@RequestParam(defaultValue = "10") int limit) {
        List<ReviewDTO> reviews = reviewService.findLimitedDTO(limit);
        return BaseResponse.success(reviews, "Fetched reviews with limit " + limit);
    }

    @GetMapping("/{id}")
    public BaseResponse<ReviewDTO> getReviewById(@PathVariable int id) {
        ReviewDTO review = reviewService.findByIdDTO(id);
        if (review == null) {
            return BaseResponse.error(
                    HttpStatus.NOT_FOUND,
                    "Review not found",
                    List.of("No review with ID = " + id));
        }
        return BaseResponse.success(review, "Fetched review successfully");
    }

    // Nếu không có useFul thì chỉ lọc theo gameId
    @GetMapping("/by-game")
    public BaseResponse<List<ReviewDTO>> getReviewsByGameIdAndUseFul(
            @RequestParam int gameId,
            @RequestParam(required = false) Integer useFul) {

        List<ReviewDTO> reviews;

        if (useFul == null) {
            reviews = reviewService.findByGameIdDTO(gameId);
        } else if (useFul.intValue() == 0) {
            reviews = reviewService.findByGameIdAndUseFulEqual(gameId, 0);
        } else {
            reviews = reviewService.findByGameIdAndUseFulGreaterThan(gameId, 0);
        }
        System.out.println("useFul param received: " + useFul);

        return BaseResponse.success(reviews, "Fetched reviews for gameId = " + gameId + " with useFul = " + useFul);
    }

    @GetMapping("/by-user")
    public BaseResponse<List<ReviewDTO>> getReviewsByUserId(@RequestParam int userId) {
        List<ReviewDTO> reviews = reviewService.findByUserIdDTO(userId);
        return BaseResponse.success(reviews, "Fetched reviews for userId = " + userId);
    }

    @PostMapping("/review-game")
    public ResponseEntity<?> review(@Valid @RequestBody ReviewRequest request) {
        reviewGameService.create(request);
        return ResponseEntity.ok(BaseResponse.success(null, "Review submitted successfully"));
    }

}