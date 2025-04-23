package com.A4Team.GamesShop.repository;

import com.A4Team.GamesShop.entities.OrderPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderPurchaseRepository extends JpaRepository<OrderPurchase, Integer> {

    // // Tìm kiếm các đơn hàng mua theo purchaseId
    // List<OrderPurchase> findByPurchaseId(int purchaseId);

    // // Tìm kiếm các đơn hàng mua theo gameKey
    // List<OrderPurchase> findByGameKey(String gameKey);

    // // Tìm kiếm các đơn hàng mua theo trạng thái activated (true/false)
    // List<OrderPurchase> findByActivated(boolean activated);

    // // Tìm kiếm các đơn hàng mua theo userId từ bảng Purchase liên kết
    // List<OrderPurchase> findByPurchase_UserId(int userId);
}
