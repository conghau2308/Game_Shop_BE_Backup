package com.A4Team.GamesShop.model.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.A4Team.GamesShop.enums.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthResponse {
    private int id;
    private String email;
    private String nickname;
    private String avatar;
    private UserRoleEnum role;
    private LocalDateTime createdAt;
    private LocalDate birthDate;
    private String firstName;
    private String lastName;


}
