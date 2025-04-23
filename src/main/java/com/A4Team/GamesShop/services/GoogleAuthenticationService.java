package com.A4Team.GamesShop.services;


import com.A4Team.GamesShop.entities.User;
import com.A4Team.GamesShop.enums.UserRoleEnum;
import com.A4Team.GamesShop.exception.GoogleAuthenticationException;
import com.A4Team.GamesShop.model.response.BaseResponse;
import com.A4Team.GamesShop.model.response.UserAuthResponse;
import com.A4Team.GamesShop.repository.UserRepository;

import com.A4Team.GamesShop.utils.GoogleAuthenticationHelper;
import com.A4Team.GamesShop.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GoogleAuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GoogleAuthenticationHelper googleAuthenticationHelper;

    @Autowired
    private JwtHelper jwtHelper;

    public BaseResponse<?> generateToken(String authorizationCode) {
        try {
            Map<String, Object> userInfo = googleAuthenticationHelper.getGoogleUserInfo(authorizationCode);

            String email = (String) userInfo.get("email");
            if (email == null || email.isEmpty()) {
                throw new GoogleAuthenticationException("Google did not return an email", HttpStatus.BAD_REQUEST);
            }
            User user = getOrCreateUser(userInfo);

            UserAuthResponse userDTO = new UserAuthResponse(user.getId(), user.getEmail(), user.getNickName(), user.getAvatar(), user.getRole(), user.getCreatedAt(),
            user.getBirthDate(), user.getFirstName(), user.getLastName());

            return BaseResponse.success(jwtHelper.generateToken(userDTO), "Generated token successfully");
        } catch (GoogleAuthenticationException e) {
            return BaseResponse.error(e.getStatus(), e.getMessage(), List.of("Google authentication failed"));
        } catch (Exception e) {
            return BaseResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occurred", List.of(e.getMessage()));
        }


    }

    private User getOrCreateUser(Map<String, Object> userInfo) {
        String email = (String) userInfo.get("email");
        String firstName = (String) userInfo.get("firstName");
        String lastName = (String) userInfo.get("lastName");
        String avatar = (String) userInfo.get("picture");

        Optional<User> existingUser = userRepository.findByEmail(email);
        User user = existingUser.orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setRole(UserRoleEnum.ROLE_USER);
            newUser.setStatus(1);
            newUser.setCreatedAt(LocalDateTime.now());
            return newUser;
        });

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAvatar(avatar);
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);

    }


    public BaseResponse<String> googleAuthUrl() {
        String url = googleAuthenticationHelper.getGoogleAuthUrl();
        BaseResponse result = BaseResponse.error(HttpStatus.BAD_REQUEST, "Invalid request: Unable to generate Google auth URL", List.of("Google authentication URL is null or empty"));
        if (url != null) {
            result = BaseResponse.success(url, "Google auth url successfully!");
        }
        return result;
    }









}
