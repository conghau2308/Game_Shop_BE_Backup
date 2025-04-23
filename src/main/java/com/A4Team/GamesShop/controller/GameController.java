package com.A4Team.GamesShop.controller;

import com.A4Team.GamesShop.dto.GameMinimalDetail;
import com.A4Team.GamesShop.entities.Game;
import com.A4Team.GamesShop.entities.GameWithPlatform;
import com.A4Team.GamesShop.model.response.BaseResponse;
import com.A4Team.GamesShop.services.games.GameService;
import com.A4Team.GamesShop.services.games.GameWithDetailService;
import com.A4Team.GamesShop.services.games.GameWithPlatformService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameWithPlatformService gameWithPlatformService;

    @Autowired
    private GameWithDetailService gameWithDetailService;

    @GetMapping("/all")
    public BaseResponse<List<Game>> getAllGames() {
        List<Game> games = gameService.findAll();
        return BaseResponse.success(games, "Fetched all games successfully");
    }

    @GetMapping("/limit")
    public BaseResponse<List<Game>> getAllGames(@RequestParam(defaultValue = "10") int limit) {
        List<Game> games = gameService.findLimited(limit);
        return BaseResponse.success(games, "Fetched games with limit " + limit);
    }

    @GetMapping("/{id}")
    public BaseResponse<Game> getGameById(@PathVariable int id) {
        Game game = gameService.findById(id);
        if (game == null) {
            return BaseResponse.error(
                    HttpStatus.NOT_FOUND,
                    "Game not found",
                    List.of("No game with ID = " + id));
        }
        return BaseResponse.success(game, "Fetched game successfully");
    }

    @GetMapping("/all/platforms")
    public BaseResponse<List<GameWithPlatform>> getGamePlatforms() {
        List<GameWithPlatform> gameWithPlatforms = gameWithPlatformService.findAll();
        return BaseResponse.success(gameWithPlatforms, "Fetched all games with platforms successfully");
    }

    @GetMapping("/{id}/platforms")
    public BaseResponse<List<Map<String, Object>>> getGamePlatforms(@PathVariable int id) {
        List<Object[]> rawData = gameWithPlatformService.findByGameId(id);

        if (rawData.isEmpty()) {
            return BaseResponse.error(
                    HttpStatus.NOT_FOUND,
                    "No platforms found",
                    List.of("No platforms available for game with ID = " + id));
        }

        List<Map<String, Object>> platforms = rawData.stream().map(row -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", row[0]);
            map.put("releaseDate", row[1]);
            map.put("isInStock", row[2]);
            map.put("name", row[3]);
            map.put("gameId", row[4]);
            map.put("finalPrice", row[5]);
            map.put("image", row[6]);
            map.put("discount_percent", row[7]);
            map.put("original_price", row[8]);
            map.put("background", row[9]);
            map.put("platformName", row[10]);
            return map;
        }).toList();

        return BaseResponse.success(platforms, "Fetched platforms for game ID = " + id);
    }

    @GetMapping("/by-platform")
    public BaseResponse<List<Map<String, Object>>> getGamesByPlatform(
            @RequestParam String platform,
            @RequestParam(defaultValue = "10") int limit) {

        List<Object[]> rawData = gameWithPlatformService.findByPlatformName(platform.toLowerCase(), limit);

        if (rawData.isEmpty()) {
            return BaseResponse.error(
                    HttpStatus.NOT_FOUND,
                    "No games found for platform",
                    List.of("No games available for platform: " + platform));
        }

        List<Map<String, Object>> result = rawData.stream().map(row -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", row[0]);
            map.put("releaseDate", row[1]);
            map.put("isInStock", row[2]);
            map.put("name", row[3]);
            map.put("gameId", row[4]);
            map.put("final_price", row[5]);
            map.put("image", row[6]);
            map.put("discount_percent", row[7]);
            map.put("description", row[8]);
            map.put("original_price", row[9]);
            map.put("background", row[10]);
            map.put("platformName", row[11]);
            return map;
        }).toList();

        return BaseResponse.success(result, "Fetched " + result.size() + " games for platform: " + platform);
    }

    @GetMapping("/{id}/detail")
    public BaseResponse<GameMinimalDetail> getGameDetailById(@PathVariable int id) {
        GameMinimalDetail detail = gameWithDetailService.getGameDetailByGameId(id);

        if (detail == null) {
            return BaseResponse.error(
                    HttpStatus.NOT_FOUND,
                    "Game detail not found",
                    List.of("No detailed info found for game with ID = " + id));
        }

        return BaseResponse.success(detail, "Fetched game detail successfully");
    }
}
