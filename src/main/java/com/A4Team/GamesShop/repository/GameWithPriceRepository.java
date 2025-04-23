package com.A4Team.GamesShop.repository;

import com.A4Team.GamesShop.entities.GameWithPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameWithPriceRepository extends JpaRepository<GameWithPrice, Integer> {
    GameWithPrice findByGameId(Integer gameId);
}
