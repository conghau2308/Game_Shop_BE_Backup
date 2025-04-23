package com.A4Team.GamesShop.controller;

import com.A4Team.GamesShop.model.request.ChangeEmailRequest;
import com.A4Team.GamesShop.model.request.ChangePasswordRequest;
import com.A4Team.GamesShop.model.request.UpdateUserRequest;
import com.A4Team.GamesShop.model.response.BaseResponse;
import com.A4Team.GamesShop.model.response.BaseResponseWithToken;
import com.A4Team.GamesShop.model.response.UserAuthResponse;
import com.A4Team.GamesShop.services.users.UserService;
import com.A4Team.GamesShop.utils.JwtHelper;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper jwtHelper;

    @GetMapping("/me")
    public ResponseEntity<BaseResponse<UserAuthResponse>> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserAuthResponse)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(BaseResponse.error(
                            HttpStatus.UNAUTHORIZED,
                            "User not authenticated",
                            List.of("Invalid token or session expired")));
        }
        UserAuthResponse user = (UserAuthResponse) authentication.getPrincipal();
        // user = userService.processUserAvatar(user);
        return ResponseEntity.ok(BaseResponse.success(user, "User retrieved successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity<BaseResponseWithToken<UserAuthResponse>> updateProfile(
            @Valid @RequestBody UpdateUserRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserAuthResponse)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(BaseResponseWithToken.error(
                            HttpStatus.UNAUTHORIZED,
                            "User not authenticated",
                            List.of("Invalid token or session expired"), null));
        }

        UserAuthResponse user = (UserAuthResponse) authentication.getPrincipal();
        UserAuthResponse updatedUser = userService.updateProfile(user.getId(), request);

        // Generate the new token after update
        String newToken = jwtHelper.generateToken(updatedUser);

        // Return the updated user data with the new token
        return ResponseEntity.ok(BaseResponseWithToken.success(updatedUser, "User updated successfully", newToken));
    }


    @PutMapping("/change-email")
    public ResponseEntity<BaseResponseWithToken<UserAuthResponse>> changeEmail(
            @Valid @RequestBody ChangeEmailRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserAuthResponse)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(BaseResponseWithToken.error(
                            HttpStatus.UNAUTHORIZED,
                            "User not authenticated",
                            List.of("Invalid token or session expired"), null));
        }

        UserAuthResponse user = (UserAuthResponse) authentication.getPrincipal();

        // Kiểm tra mật khẩu hiện tại trước khi thay đổi email
        userService.checkPassword(user.getId(), request.getCurrentPassword());

        // Thay đổi email
        UserAuthResponse updatedUser = userService.changeEmail(user.getId(), request.getNewEmail());

        // Tạo token mới sau khi thay đổi email
        String newToken = jwtHelper.generateToken(updatedUser);

        return ResponseEntity.ok(BaseResponseWithToken.success(updatedUser, "Email updated successfully", newToken));
    }


    // API thay đổi mật khẩu
    @PutMapping("/change-password")
    public ResponseEntity<BaseResponseWithToken<UserAuthResponse>> changePassword(
            @Valid @RequestBody ChangePasswordRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserAuthResponse)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(BaseResponseWithToken.error(
                            HttpStatus.UNAUTHORIZED,
                            "User not authenticated",
                            List.of("Invalid token or session expired"), null));
        }

        UserAuthResponse user = (UserAuthResponse) authentication.getPrincipal();

        // Thay đổi mật khẩu
        boolean isPasswordChanged = userService.changePassword(user.getId(), request.getOldPassword(), request.getNewPassword());

        if (!isPasswordChanged) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(BaseResponseWithToken.error(
                            HttpStatus.BAD_REQUEST,
                            "Password change failed",
                            List.of("Current password is incorrect or new password is invalid"), null));
        }

        // Tạo token mới sau khi thay đổi mật khẩu
        String newToken = jwtHelper.generateToken(user);

        return ResponseEntity.ok(BaseResponseWithToken.success(user, "Password changed successfully", newToken));
    }

}
