package com.A4Team.GamesShop.services;

import com.A4Team.GamesShop.model.response.AuthResponse;
import com.A4Team.GamesShop.model.response.UserAuthResponse;
import com.A4Team.GamesShop.entities.User;
import com.A4Team.GamesShop.enums.UserRoleEnum;
import com.A4Team.GamesShop.exception.LoginException;
import com.A4Team.GamesShop.repository.UserRepository;
import com.A4Team.GamesShop.model.request.LoginRequest;
import com.A4Team.GamesShop.model.request.RegisterRequest;
import com.A4Team.GamesShop.model.response.BaseResponse;
import com.A4Team.GamesShop.utils.JwtHelper;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;




@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtHelper jwtHelper;


    public BaseResponse<AuthResponse> login(@Valid LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new LoginException("Invalid email or password"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new LoginException("Invalid password");
        }

        UserAuthResponse userDTO = new UserAuthResponse(user.getId(), user.getEmail(), user.getNickName(), user.getAvatar(), user.getRole(), user.getCreatedAt(), 
        user.getBirthDate(), user.getFirstName(), user.getLastName());
        String token = jwtHelper.generateToken(userDTO);
        AuthResponse authDTO = new AuthResponse(token);

        return BaseResponse.success(authDTO, "Login successfully");
    }

    public BaseResponse<AuthResponse> register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return BaseResponse.error(400, "Email already exists", List.of("Email is already in use"));
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setNickName("gamer" + UUID.randomUUID().toString().substring(0, 7));
        user.setAvatar("https://gaming-cdn.com/themes/igv2/images/avatar2.svg");
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setBirthDate(request.getBirthDate());
        user.setStatus(1);
        user.setRole(UserRoleEnum.ROLE_USER);
        user.setTotalRewardPoint(0);
        user.setCreatedAt(LocalDateTime.now());
        user.setCountry_id(1);

        userRepository.save(user);

        UserAuthResponse userDTO = new UserAuthResponse(
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

        String token = jwtHelper.generateToken(userDTO);
        AuthResponse authDTO = new AuthResponse(token);

        return BaseResponse.success(authDTO, "Register successfully");
    }
}
