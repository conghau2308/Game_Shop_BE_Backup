package com.A4Team.GamesShop.services.users;

import com.A4Team.GamesShop.model.request.UpdateUserRequest;
import com.A4Team.GamesShop.model.response.UserAuthResponse;
import com.A4Team.GamesShop.repository.UserRepository;
import com.A4Team.GamesShop.entities.User;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{
    @Value("${server.url}")
    private String serverUrl;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserAuthResponse processUserAvatar(UserAuthResponse user) {
        if (user.getAvatar() != null && !user.getAvatar().isEmpty()) {
            String fileName = user.getAvatar().substring(user.getAvatar().lastIndexOf("/") + 1);
            user.setAvatar(serverUrl + "/api/files/avatar");
        }
        return user;
    }

    @Override
    public UserAuthResponse updateProfile(int userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setBirthDate(request.getBirthDate());
        user.setNickName(request.getNickName());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        return new UserAuthResponse(
                user.getId(),
                user.getEmail(),
                user.getNickName(),
                user.getAvatar(),
                user.getRole(),
                user.getCreatedAt(),
                user.getBirthDate(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    // Phương thức kiểm tra mật khẩu hiện tại
    @Override
    public boolean checkPassword(int userId, String currentPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Kiểm tra xem mật khẩu hiện tại có khớp với mật khẩu trong cơ sở dữ liệu không
        return passwordEncoder.matches(currentPassword, user.getPassword());
    }

    // Phương thức thay đổi email
    @Override
    public UserAuthResponse changeEmail(int userId, String newEmail) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Kiểm tra email mới có hợp lệ hay không (trong trường hợp bạn muốn kiểm tra email trùng lặp hoặc hợp lệ)
        if (userRepository.existsByEmail(newEmail)) {
            throw new RuntimeException("Email đã được sử dụng");
        }

        user.setEmail(newEmail);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        return new UserAuthResponse(
                user.getId(),
                user.getEmail(),
                user.getNickName(),
                user.getAvatar(),
                user.getRole(),
                user.getCreatedAt(),
                user.getBirthDate(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    // Phương thức thay đổi mật khẩu
    @Override
    public boolean changePassword(int userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Kiểm tra mật khẩu hiện tại có đúng không
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Mật khẩu hiện tại không đúng");
        }

        // Cập nhật mật khẩu mới
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        return true;
    }

}
