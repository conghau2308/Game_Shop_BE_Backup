package com.A4Team.GamesShop.controller;

import com.A4Team.GamesShop.model.request.PurchaseAllRequest;
import com.A4Team.GamesShop.model.response.BaseResponse;
import com.A4Team.GamesShop.services.purchases.PurchaseService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/order-purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    /**
     * API để tạo đơn hàng mới
     */
    @PostMapping("/order")
    public ResponseEntity<BaseResponse<Void>> createOrderPurchase(@Valid @RequestBody PurchaseAllRequest orderRequest) {
        // Truyền cả PurchaseRequest và PurchaseHistoryRequest vào service
        purchaseService.createPurchase(orderRequest.getPurchaseRequest(), orderRequest.getPurchaseHistoryRequest());

        // Trả về phản hồi thành công
        return ResponseEntity.ok(BaseResponse.success(null, "OrderPurchase created successfully"));
    }

}
