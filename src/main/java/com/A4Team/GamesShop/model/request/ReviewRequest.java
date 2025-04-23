package com.A4Team.GamesShop.model.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewRequest {
    @NotNull(message = "User Id is required")
    @JsonProperty("userId")
    private Integer userId;

    @NotNull(message = "Game Id is required")
    @JsonProperty("gameId")
    private Integer gameId;

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @NotBlank(message = "Status is required")
    @JsonProperty("status")
    private String status;

    @NotNull(message = "useful is required")
    @JsonProperty("useFul")
    private Integer useFul;

    @NotBlank(message = "Comment is required")
    @JsonProperty("comment")
    private String comment;

}
