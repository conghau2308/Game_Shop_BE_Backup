package com.A4Team.GamesShop.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
public class BaseResponseWithToken<T> {
    private int statusCode;
    private String message;
    private T data;
    private List<String> errors;
    private String token;  // Chỉ thêm token vào khi cần thiết

    // Cập nhật phương thức success để trả về token
    public static <T> BaseResponseWithToken<T> success(T data, String message, String token) {
        return new BaseResponseWithToken<>(HttpStatus.OK.value(), message, data, null, token);
    }

    // Cập nhật phương thức error để trả về token nếu cần
    public static <T> BaseResponseWithToken<T> error(HttpStatus status, String message, List<String> errors, String token) {
        return new BaseResponseWithToken<>(status.value(), message, null, errors, token);
    }

    public static <T> BaseResponseWithToken<T> error(int statusCode, String message, List<String> errors) {
        return new BaseResponseWithToken<T>(statusCode, message, null, errors, null);
    }
}
