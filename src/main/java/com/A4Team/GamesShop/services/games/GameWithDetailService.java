package com.A4Team.GamesShop.services.games;

import com.A4Team.GamesShop.dto.GameMinimalDetail;
import com.A4Team.GamesShop.repository.GameWithDetailRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class GameWithDetailService {

    private final GameWithDetailRepository gameWithDetailRepository;

    // @Cacheable("GameDetails")
    public GameMinimalDetail getGameDetailByGameId(int gameId) {
        List<Object[]> rows = gameWithDetailRepository.findMinimalDetailByGameId(gameId);
    
        if (rows.isEmpty()) return null;
    
        Object[] row = rows.get(0);
        return new GameMinimalDetail(
            (Date) row[0],
            (String) row[1],
            (String) row[2],
            (String) row[3],
            (String) row[4],
            row[5] != null ? ((Number) row[5]).intValue() : 0, // ratingPoint
            row[6] != null ? ((Number) row[6]).longValue() : 0L, // reviews
            (Integer) row[7]
        );
    }
    

    // Nếu muốn hỗ trợ lấy nhiều bản ghi cho 1 game (theo platform chẳng hạn)
    // @Cacheable("GameDatasDetails")
    public List<GameMinimalDetail> getGameDetailsByGameId(int gameId) {
        return gameWithDetailRepository.findMinimalDetailByGameId(gameId)
            .stream()
            .map(row -> new GameMinimalDetail(
                (Date) row[0],
                (String) row[1],
                (String) row[2],
                (String) row[3],
                (String) row[4],
                row[5] != null ? ((Number) row[5]).intValue() : 0,
                row[6] != null ? ((Number) row[6]).longValue() : 0L,
                (Integer) row[7]
            ))
            .collect(Collectors.toList());
    }    
}
