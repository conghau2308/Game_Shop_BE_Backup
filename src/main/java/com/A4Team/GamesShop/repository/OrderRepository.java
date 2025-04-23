package com.A4Team.GamesShop.repository;

import com.A4Team.GamesShop.dto.OrderDTO;
import com.A4Team.GamesShop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = """
    SELECT 
        ph.id AS orderId,
        ph.activated,
        ph.purchase_date AS purchaseDate,
        ph.game_key AS gameKey,
        p.created_at AS createdAt,
        p.total_price AS totalPrice,
        p.game_id AS gameId,
        g.name AS gameName,
        g.image AS gameImage
    FROM payment p
    JOIN purchase_history ph ON ph.payment_id = p.id
    JOIN game g ON p.game_id = g.id
    WHERE p.user_id = :userId
    ORDER BY p.created_at DESC
    """, nativeQuery = true)
    List<Object[]> findByUserIdNative(@Param("userId") Integer userId);
}