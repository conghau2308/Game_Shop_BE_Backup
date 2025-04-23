package com.A4Team.GamesShop.services.orders;

import com.A4Team.GamesShop.dto.OrderDTO;
import com.A4Team.GamesShop.entities.Order;
import com.A4Team.GamesShop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // @Cacheable("allOrder")
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<OrderDTO> findByUserId(int userId) {
        List<Object[]> raw = orderRepository.findByUserIdNative(userId);
        return raw.stream().map(row -> new OrderDTO(
            (Integer) row[0],                                      // orderId
            (Boolean) row[1],                                      // activated
            ((java.sql.Timestamp) row[2]).toLocalDateTime(),       // purchaseDate
            (String) row[3],
            ((java.sql.Timestamp) row[4]).toLocalDateTime(),       // createdAt
            (BigDecimal) row[5],                                   // totalPrice
            (Integer) row[6],
            (String) row[7],                                       // gameName
            (String) row[8]                                      // gameImage
        )).toList();
    }    
}