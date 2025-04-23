package com.A4Team.GamesShop.model.request;

import lombok.Data;

@Data
public class PurchaseAllRequest {
    private PurchaseRequest purchaseRequest;
    private PurchaseHistoryRequest purchaseHistoryRequest;
}
