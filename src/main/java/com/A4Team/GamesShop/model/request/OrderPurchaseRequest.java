package com.A4Team.GamesShop.model.request;

import lombok.Data;

@Data
public class OrderPurchaseRequest {

    private int purchaseId;
    private String downloadUrl;
    private int rewardPoint;

}
