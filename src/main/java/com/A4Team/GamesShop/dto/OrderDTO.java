package com.A4Team.GamesShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Integer orderId;         // purchase_history.id
    private Boolean activated;
    private LocalDateTime purchaseDate;
    private String gameKey;

    private LocalDateTime createdAt;
    private BigDecimal totalPrice;
    private Integer gameId;

    private String gameName;
    private String gameImage;
}
