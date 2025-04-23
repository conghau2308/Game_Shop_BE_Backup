package com.A4Team.GamesShop.services.games;

import com.A4Team.GamesShop.entities.Game;
import com.A4Team.GamesShop.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    // @Cacheable("allGames")
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    // @Cacheable(value = "limitedGames", key = "#limit")
    public List<Game> findLimited(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return gameRepository.findAll(pageable).getContent();
    }

    public Game findById(int id) {
        return gameRepository.findById(id).orElse(null);
    }
}
