package com.A4Team.GamesShop.model.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PurchaseHistoryRequest {

    @NotBlank(message = "Game Key is required")
    @JsonProperty("gameKey")
    private String gameKey; // Khóa game

    @JsonProperty("activated")
    private Boolean activated = false; // Mặc định là false

    @NotBlank(message = "Download URL is required")
    @JsonProperty("downloadUrl")
    private String downloadUrl; // Địa chỉ tải về

    @NotNull(message = "Reward Points is required")
    @JsonProperty("rewardPoint")
    private Integer rewardPoint; // Điểm thưởng

    @JsonProperty("purchase_date")
    private LocalDateTime purchaseDate;

}
