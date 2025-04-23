package com.A4Team.GamesShop.repository;

import com.A4Team.GamesShop.entities.GameWithPlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameWithPlatformRepository extends JpaRepository<GameWithPlatform, Integer> {

    @Query(value = """
            SELECT
                gp.id, gp.release_date, gp.is_in_stock,
                gwp.name, gwp.game_id, gwp.final_price,
                gwp.image, gwp.discount_percent,
                gwp.original_price, gwp.background,
                p.name AS platform_name
            FROM game_platforms gp
            JOIN game_with_prices gwp ON gp.game_id = gwp.game_id
            JOIN platform p ON gp.platform_id = p.id
            WHERE gp.game_id = :gameId
            """, nativeQuery = true)
    List<Object[]> findByGameIdNative(@Param("gameId") Integer gameId);

    @Query(value = """
            SELECT
                gp.id, gp.release_date, gp.is_in_stock,
                gwp.name, gwp.game_id, gwp.final_price,
                gwp.image, gwp.discount_percent, gwp.description,
                gwp.original_price, gwp.background,
                p.name AS platform_name
            FROM game_platforms gp
            JOIN game_with_prices gwp ON gp.game_id = gwp.game_id
            JOIN platform p ON gp.platform_id = p.id
            WHERE LOWER(p.name) = LOWER(:platformName)
            """, nativeQuery = true)
    List<Object[]> findByPlatformName(@Param("platformName") String platformName,
            org.springframework.data.domain.Pageable pageable);
}
