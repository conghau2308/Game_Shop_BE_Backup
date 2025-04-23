package com.A4Team.GamesShop.dto;

import com.A4Team.GamesShop.entities.OrderPurchase;
import lombok.Data;

@Data
public class OrderPurchaseDTO {

    private int id;
    private String gameKey;
    private String downloadUrl;
    private int rewardPoint;

    public OrderPurchaseDTO(OrderPurchase orderPurchase) {
        this.id = orderPurchase.getId();
        this.gameKey = orderPurchase.getGameKey();
        this.downloadUrl = orderPurchase.getDownloadUrl();
        this.rewardPoint = orderPurchase.getRewardPoint();
    }
}
