package com.A4Team.GamesShop.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.A4Team.GamesShop.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByGameId(Integer gameId);

    List<Review> findByUserId(Integer userId);

    List<Review> findByGameIdAndUseFul(Integer gameId, Integer useFul);

    List<Review> findByGameIdAndUseFulGreaterThan(Integer gameId, Integer threshold);
}
