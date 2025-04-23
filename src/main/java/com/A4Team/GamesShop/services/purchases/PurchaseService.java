package com.A4Team.GamesShop.services.purchases;

import com.A4Team.GamesShop.dto.PurchaseDTO;
import com.A4Team.GamesShop.entities.Purchase;
import com.A4Team.GamesShop.entities.PurchaseHistory;
import com.A4Team.GamesShop.model.request.PurchaseRequest;
import com.A4Team.GamesShop.model.request.PurchaseHistoryRequest;
import com.A4Team.GamesShop.repository.PurchaseRepository;
import com.A4Team.GamesShop.repository.PurchaseHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseHistoryRepository purchaseHistoryRepository;

    /**
     * Xử lý và lưu đơn hàng thành công
     *
     * @param request đối tượng yêu cầu tạo đơn hàng
     * @return đơn hàng đã được lưu vào DB
     */
    public Purchase createPurchase(PurchaseRequest request, PurchaseHistoryRequest historyRequest) {
        // Tạo đối tượng Purchase từ request
        Purchase purchase = new Purchase();
        purchase.setUserId(request.getUserId());
        purchase.setGameId(request.getGameId());
        purchase.setPaymentType(request.getPaymentType());
        purchase.setTotalPrice(request.getTotalPrice());
        purchase.setStatus(request.getStatus()); // Trạng thái đơn hàng (0: Pending, 1: Completed)
        
        // Lưu thông tin vào cơ sở dữ liệu bảng purchase
        purchase = purchaseRepository.save(purchase);
    
        // Sau khi lưu purchase, tạo bản ghi trong bảng purchase_history
        PurchaseHistory purchaseHistory = new PurchaseHistory();
        System.out.println("ID cua payment " + purchase.getId());
        purchaseHistory.setPaymentId(purchase.getId()); // Lấy id vừa lưu từ bảng purchase
        purchaseHistory.setGameKey(generateGameKey()); // Tạo game_key tự động
        purchaseHistory.setDownloadUrl(historyRequest.getDownloadUrl()); // Thêm downloadUrl từ request
        purchaseHistory.setRewardPoint(0); // Lưu reward point từ request
    
        purchaseHistoryRepository.save(purchaseHistory); // Lưu vào bảng purchase_history
        System.out.println("Dang luu puchase_history Id: " + purchaseHistory.getId());
    
        return purchase;
    }    

    /**
     * Lấy thông tin đơn hàng theo ID
     *
     * @param paymentId ID của đơn hàng
     * @return đối tượng Purchase nếu tìm thấy, ngược lại trả về Optional.empty()
     */
    public Optional<Purchase> getPurchaseById(int paymentId) {
        return purchaseRepository.findById(paymentId);
    }


    /**
     * Phương thức để tạo game_key theo định dạng 'AAA-22L-GGG_TTT'
     * 
     * @return game_key tự động
     */
    private String generateGameKey() {
        // Tạo chuỗi game_key ngẫu nhiên với định dạng 'AAA-22L-GGG_TTT'
        String part1 = generateRandomString(3).toUpperCase(); // 3 ký tự ngẫu nhiên (chữ hoa)
        String part2 = generateRandomNumber(2); // 2 số ngẫu nhiên
        String part3 = generateRandomString(1).toUpperCase(); // 1 ký tự ngẫu nhiên (chữ hoa)
        String part4 = generateRandomString(3).toUpperCase(); // 3 ký tự ngẫu nhiên (chữ hoa)
        String part5 = generateRandomNumber(3); // 3 số ngẫu nhiên

        return part1 + "-" + part2 + part3 + "-" + part4 + "_" + part5;
    }


    /**
     * Hàm tạo chuỗi ngẫu nhiên với độ dài nhất định
     * 
     * @param length độ dài chuỗi
     * @return chuỗi ngẫu nhiên
     */
    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }


    /**
     * Hàm tạo số ngẫu nhiên với độ dài nhất định
     * 
     * @param length độ dài số
     * @return số ngẫu nhiên
     */
    private String generateRandomNumber(int length) {
        String digits = "0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * digits.length());
            sb.append(digits.charAt(index));
        }
        return sb.toString();
    }


    /**
     * Lấy tất cả đơn hàng của một user
     *
     * @param userId ID của người dùng
     * @return danh sách các đơn hàng của người dùng
     */
    public List<PurchaseDTO> findPurchasesByUserId(int userId) {
        List<Purchase> purchases = purchaseRepository.findByUserId(userId);
        return purchases.stream().map(purchase -> new PurchaseDTO(purchase)).toList();
    }

    /**
     * Lấy tất cả đơn hàng
     *
     * @return danh sách tất cả các đơn hàng
     */
    public List<PurchaseDTO> findAllPurchases() {
        List<Purchase> purchases = purchaseRepository.findAll();
        return purchases.stream().map(purchase -> new PurchaseDTO(purchase)).toList();
    }
}
