package com.A4Team.GamesShop.dto;

import com.A4Team.GamesShop.entities.Purchase;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PurchaseDTO {

    private int id;
    private Integer userId;
    private Integer gameId;
    private String paymentType;
    private Integer status;
    private LocalDateTime createdAt;
    private BigDecimal totalPrice;

    public PurchaseDTO(Purchase purchase) {
        this.id = purchase.getId();
        this.userId = purchase.getUserId();
        this.gameId = purchase.getGameId();
        this.paymentType = purchase.getPaymentType();
        this.status = purchase.getStatus();
        this.totalPrice = purchase.getTotalPrice();
    }
}
