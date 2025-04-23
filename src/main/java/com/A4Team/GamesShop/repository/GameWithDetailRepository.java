package com.A4Team.GamesShop.repository;

import com.A4Team.GamesShop.entities.GameWithPlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameWithDetailRepository extends JpaRepository<GameWithPlatform, Integer> {

    @Query(value = """
                SELECT
                gp.release_date,
                g.description AS game_description,
                pub.name AS publisher_name,
                dev.name AS developer_name,
                GROUP_CONCAT(DISTINCT ge.name ORDER BY ge.name SEPARATOR ', ') AS genre_names,
                FLOOR(SUM(CASE WHEN r.status = 'like' THEN 1 ELSE 0 END) * 10.0 / NULLIF(COUNT(r.id), 0)) AS rating_point,
                COUNT(r.id) AS review_count,
                r.game_id AS game_id
            FROM game_platforms gp
            JOIN game g ON g.id = gp.game_id
            JOIN publisher pub ON g.publisher_id = pub.id
            JOIN developer dev ON g.developer_id = dev.id
            LEFT JOIN game_genre gg ON g.id = gg.game_id
            LEFT JOIN genre ge ON gg.genre_id = ge.id
            LEFT JOIN reviews r ON g.id = r.game_id
            WHERE gp.game_id = :gameId
            GROUP BY gp.release_date, g.description, pub.name, dev.name;
                """, nativeQuery = true)
    List<Object[]> findMinimalDetailByGameId(@Param("gameId") Integer gameId);
}
