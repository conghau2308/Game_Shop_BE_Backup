package com.A4Team.GamesShop.repository;

import com.A4Team.GamesShop.entities.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Integer> {

}
