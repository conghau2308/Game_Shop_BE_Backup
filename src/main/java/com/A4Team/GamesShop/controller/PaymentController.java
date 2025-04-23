package com.A4Team.GamesShop.controller;

import com.A4Team.GamesShop.services.payments.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@org.springframework.stereotype.Controller
@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:8082")  // Cấu hình CORS cho frontend

public class PaymentController {
    @Autowired
    private VNPayService vnPayService;

    // Chuyển từ orderId sang truyền amount trực tiếp
    @PostMapping("/submitOrder")
    public String submitOrder(@RequestParam("amount") long amount,  // Truyền số tiền thay vì id
                              HttpServletRequest request) {
        // String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        // Gọi service để tạo URL thanh toán với số tiền
        String baseUrl = request.getHeader("Origin");
        System.out.println("Base URL: " + baseUrl);
        String returnUrl = baseUrl + "/checkPayment";
        String vnpayUrl = vnPayService.createOrder(amount, returnUrl);
        return "redirect:" + vnpayUrl;  // Redirect tới URL thanh toán VNPay
    }

    // Xử lý callback khi thanh toán xong
    //Hiện tại api không dùng đển returnOrder để giải mã mà sử dụng vnp_TransactionStatus có được từ retunr_url của vnpay-payment
    @GetMapping("/vnpay-payment")
    public String vnpayPayment(@RequestParam Map<String, String> allParams, Model model) {
        // Lấy các tham số từ request
        String vnp_Amount = allParams.get("vnp_Amount");
        String vnp_TransactionStatus = allParams.get("vnp_TransactionStatus");
        String vnp_TransactionNo = allParams.get("vnp_TransactionNo");

        model.addAttribute("vnp_Amount", vnp_Amount);
        model.addAttribute("vnp_TransactionStatus", vnp_TransactionStatus);
        model.addAttribute("vnp_TransactionNo", vnp_TransactionNo);

        // Xử lý logic kiểm tra trạng thái thanh toán
        if ("00".equals(vnp_TransactionStatus)) {
            return "ordersuccess";  // Thanh toán thành công
        } else {
            return "orderfail";  // Thanh toán thất bại
        }
    }
}
