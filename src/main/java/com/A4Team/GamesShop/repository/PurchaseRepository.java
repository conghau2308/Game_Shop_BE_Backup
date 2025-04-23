package com.A4Team.GamesShop.repository;

import com.A4Team.GamesShop.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findByUserId(int userId);
}
