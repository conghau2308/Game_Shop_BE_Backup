package com.A4Team.GamesShop.services.purchases;

import com.A4Team.GamesShop.dto.OrderPurchaseDTO;
import com.A4Team.GamesShop.entities.OrderPurchase;
import com.A4Team.GamesShop.entities.Purchase;
import com.A4Team.GamesShop.repository.OrderPurchaseRepository;
import com.A4Team.GamesShop.repository.PurchaseRepository;
import com.A4Team.GamesShop.model.request.OrderPurchaseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class OrderPurchaseService {

    @Autowired
    private OrderPurchaseRepository orderPurchaseRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * Tạo game_key ngẫu nhiên theo định dạng 'AAA-22L-GGG_TTT'
     *
     * @return game_key ngẫu nhiên
     */
    private String generateRandomGameKey() {
        SecureRandom random = new SecureRandom();
        StringBuilder gameKey = new StringBuilder();

        // Tạo phần đầu 'AAA'
        for (int i = 0; i < 3; i++) {
            gameKey.append(ALPHANUMERIC.charAt(random.nextInt(26))); // Chọn chữ cái
        }

        gameKey.append("-");

        // Tạo phần '22L'
        for (int i = 0; i < 2; i++) {
            gameKey.append(ALPHANUMERIC.charAt(random.nextInt(10) + 26)); // Chọn số
        }
        gameKey.append(ALPHANUMERIC.charAt(random.nextInt(26) + 36)); // Chọn chữ cái

        gameKey.append("-");

        // Tạo phần 'GGG'
        for (int i = 0; i < 3; i++) {
            gameKey.append(ALPHANUMERIC.charAt(random.nextInt(26))); // Chọn chữ cái
        }

        gameKey.append("_");

        // Tạo phần 'TTT'
        for (int i = 0; i < 3; i++) {
            gameKey.append(ALPHANUMERIC.charAt(random.nextInt(36))); // Chọn cả số và chữ
        }

        return gameKey.toString();
    }

    /**
     * Tạo và lưu đơn hàng OrderPurchase
     *
     * @param request đối tượng yêu cầu tạo đơn hàng
     * @return đơn hàng OrderPurchase đã được lưu vào DB
     */
    public OrderPurchase createOrderPurchase(OrderPurchaseRequest request) {
        // Tìm Purchase tương ứng với purchaseId
        Purchase purchase = purchaseRepository.findById(request.getPurchaseId())
                .orElseThrow(() -> new IllegalArgumentException("Purchase not found"));

        // Tạo game_key ngẫu nhiên
        String gameKey = generateRandomGameKey();

        // Tạo đối tượng OrderPurchase từ request và purchase
        OrderPurchase orderPurchase = new OrderPurchase();
        // orderPurchase.setPurchase(purchase); // Liên kết với Purchase entity
        orderPurchase.setGameKey(gameKey);  // Đặt game_key ngẫu nhiên
        orderPurchase.setDownloadUrl(request.getDownloadUrl());
        orderPurchase.setRewardPoint(request.getRewardPoint());

        // Lưu vào cơ sở dữ liệu
        return orderPurchaseRepository.save(orderPurchase);
    }

    /**
     * Lấy tất cả đơn hàng OrderPurchase
     *
     * @return danh sách các đơn hàng OrderPurchase
     */
    public List<OrderPurchaseDTO> findAllOrderPurchases() {
        List<OrderPurchase> orderPurchases = orderPurchaseRepository.findAll();
        return orderPurchases.stream().map(orderPurchase -> new OrderPurchaseDTO(orderPurchase)).toList();
    }

    /**
     * Lấy đơn hàng OrderPurchase theo ID
     *
     * @param orderPurchaseId ID của đơn hàng
     * @return đối tượng OrderPurchase nếu tìm thấy, ngược lại trả về null
     */
    public OrderPurchase getOrderPurchaseById(int orderPurchaseId) {
        return orderPurchaseRepository.findById(orderPurchaseId).orElse(null);
    }
}
