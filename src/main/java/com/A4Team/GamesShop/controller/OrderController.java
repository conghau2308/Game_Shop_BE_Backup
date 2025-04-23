package com.A4Team.GamesShop.controller;

import com.A4Team.GamesShop.dto.OrderDTO;
import com.A4Team.GamesShop.entities.Order;
import com.A4Team.GamesShop.model.response.BaseResponse;
import com.A4Team.GamesShop.services.orders.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    public BaseResponse<List<Order>> getAllOrders() {
        List<Order> orders = orderService.findAll();
        return BaseResponse.success(orders, "Fetched all orders successfully");
    }

    @GetMapping("/user/{userId}")
    public BaseResponse<List<OrderDTO>> getOrdersByUserId(@PathVariable int userId) {
        List<OrderDTO> orders = orderService.findByUserId(userId);
        if (orders.isEmpty()) {
            return BaseResponse.error(
                    HttpStatus.NOT_FOUND,
                    "No orders found for user",
                    List.of("No orders found for user ID = " + userId));
        }
        return BaseResponse.success(orders, "Fetched orders for user successfully");
    }
}
