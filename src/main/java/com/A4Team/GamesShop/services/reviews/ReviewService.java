package com.A4Team.GamesShop.services.reviews;

import com.A4Team.GamesShop.dto.ReviewDTO;
import com.A4Team.GamesShop.mapper.ReviewMapper;
import com.A4Team.GamesShop.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    // @Cacheable("allReviews")
    public List<ReviewDTO> findAllDTO() {
        return reviewRepository.findAll().stream()
                .map(ReviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    // @Cacheable(value = "limitedReviews", key = "#limit")
    public List<ReviewDTO> findLimitedDTO(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return reviewRepository.findAll(pageable).getContent().stream()
                .map(ReviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ReviewDTO findByIdDTO(int id) {
        return reviewRepository.findById(id)
                .map(ReviewMapper::toDTO)
                .orElse(null);
    }

    // @Cacheable("reviewsByGameId")
    public List<ReviewDTO> findByGameIdDTO(Integer gameId) {
        return reviewRepository.findByGameId(gameId).stream()
                .map(ReviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    // @Cacheable("reviewsByUserId")
    public List<ReviewDTO> findByUserIdDTO(Integer userId) {
        return reviewRepository.findByUserId(userId).stream()
                .map(ReviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Lọc reviews theo gameId và use_ful = 0
    public List<ReviewDTO> findByGameIdAndUseFulEqual(int gameId, int useFul) {
        return reviewRepository.findByGameIdAndUseFul(gameId, useFul).stream()
                .map(ReviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Lọc reviews theo gameId và use_ful > 0
    public List<ReviewDTO> findByGameIdAndUseFulGreaterThan(int gameId, int threshold) {
        return reviewRepository.findByGameIdAndUseFulGreaterThan(gameId, threshold).stream()
                .map(ReviewMapper::toDTO)
                .collect(Collectors.toList());
    }
}
