package com.A4Team.GamesShop.repository;

import com.A4Team.GamesShop.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    Page<Game> findAll(Pageable pageable);
}
