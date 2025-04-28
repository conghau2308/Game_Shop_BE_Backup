package com.A4Team.GamesShop.entities;

import com.A4Team.GamesShop.enums.UserRoleEnum;
import com.A4Team.GamesShop.enums.UserStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // @Column(name="google_code")
    // private String googleCode;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "nickname")
    private String nickName;

    @Column(name="avatar")
    private String avatar;

    @Column(name = "birthdate")
    private LocalDate birthDate;

    @Column(name="created_at", updatable=false)
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "total_reward_point", columnDefinition = "int default 0")
    private int totalRewardPoint;

    @Column(name = "country_id")
    private int country_id;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private UserRoleEnum role;


    @Column(name="status")
    private int status;

    public UserStatusEnum getStatusEnum() {
        return UserStatusEnum.fromValue(this.status);
    }

    public void setStatusEnum(UserStatusEnum status) {
        this.status = status.getValue();
    }


    public User(String email, String firstName, String lastName, String avatar) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

}
