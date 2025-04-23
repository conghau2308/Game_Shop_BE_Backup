package com.A4Team.GamesShop.services.games;

import com.A4Team.GamesShop.entities.GameWithPrice;
import com.A4Team.GamesShop.repository.GameWithPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Service
public class GameWithPricessService {

    @Autowired
    private GameWithPriceRepository gameWithPriceRepository;

    // @Cacheable("allGamesWithPrice")
    public List<GameWithPrice> findAll() {
        return gameWithPriceRepository.findAll();
    }

    // @Cacheable(value = "limitedGamesWithPrice", key = "#limit")
    public List<GameWithPrice> findLimited(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return gameWithPriceRepository.findAll(pageable).getContent();
    }

    public GameWithPrice findByGameId(int gameId) {
        return gameWithPriceRepository.findByGameId(gameId);
    }
}
