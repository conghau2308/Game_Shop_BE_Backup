package com.A4Team.GamesShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserShortDTO {
    private Integer id;
    private String nickname;
    private String avatar;
}
