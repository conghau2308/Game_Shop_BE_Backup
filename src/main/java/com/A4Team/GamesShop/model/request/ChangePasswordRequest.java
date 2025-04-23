package com.A4Team.GamesShop.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangePasswordRequest {

    @NotBlank(message = "Current password is requied")
    private String oldPassword;  // Mật khẩu hiện tại của người dùng

    @NotBlank(message = "New password is required")
    private String newPassword;  // Mật khẩu mới mà người dùng muốn thay đổi
}
