package com.A4Team.GamesShop.services.users;

import com.A4Team.GamesShop.model.request.UpdateUserRequest;
import com.A4Team.GamesShop.model.response.UserAuthResponse;

public interface UserService {
    UserAuthResponse processUserAvatar(UserAuthResponse user);
    UserAuthResponse updateProfile(int userId, UpdateUserRequest request);

    // Phương thức kiểm tra mật khẩu
    boolean checkPassword(int userId, String currentPassword);

    // Phương thức thay đổi email
    UserAuthResponse changeEmail(int userId, String newEmail);

    // Phương thức thay đổi mật khẩu
    boolean changePassword(int userId, String oldPassword, String newPassword);
}
