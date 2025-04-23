package com.A4Team.GamesShop.model.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.Data;

@Data
public class PurchaseRequest {

    @NotNull(message = "User Id is required")
    @JsonProperty("userId")
    private Integer userId;

    @NotNull(message = "Game Id is required")
    @JsonProperty("gameId")
    private Integer gameId;

    @NotBlank(message = "Payment Type is required")
    @JsonProperty("paymentType")
    private String paymentType;

    @NotNull(message = "Total Price is required")
    @Positive(message = "Total Price must be positive")
    @JsonProperty("totalPrice")
    private BigDecimal totalPrice;

    @NotNull(message = "Status is required")
    @JsonProperty("status")
    private Integer status; // 0: Pending, 1: Completed

    @JsonProperty("createdAt")
    private LocalDateTime createdAt; // Default to current timestamp if not provided

}
