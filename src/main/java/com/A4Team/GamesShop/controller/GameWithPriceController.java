package com.A4Team.GamesShop.controller;

import com.A4Team.GamesShop.entities.GameWithPrice;
import com.A4Team.GamesShop.model.response.BaseResponse;
import com.A4Team.GamesShop.services.games.GameWithPricessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/games-prices")
public class GameWithPriceController {

    @Autowired
    private GameWithPricessService gameWithPriceService;

    @GetMapping("/all")
    public BaseResponse<List<GameWithPrice>> getAllGamesWithPrice() {
        List<GameWithPrice> games = gameWithPriceService.findAll();
        return BaseResponse.success(games, "Fetched all games with prices successfully");
    }

    @GetMapping("/limit")
    public BaseResponse<List<GameWithPrice>> getLimitedGamesWithPrice(@RequestParam(defaultValue = "10") int limit) {
        List<GameWithPrice> games = gameWithPriceService.findLimited(limit);
        return BaseResponse.success(games, "Fetched games with prices (limit " + limit + ")");
    }

    @GetMapping("/{game_id}")
    public BaseResponse<GameWithPrice> getGameWithPriceByGameId(@PathVariable int gameId) {
        GameWithPrice game = gameWithPriceService.findByGameId(gameId);
        if (game == null) {
            return BaseResponse.error(
                    HttpStatus.NOT_FOUND,
                    "Game with price not found",
                    List.of("No game with ID = " + gameId));
        }
        return BaseResponse.success(game, "Fetched game with price successfully");
    }
}
