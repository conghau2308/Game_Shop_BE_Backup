package com.A4Team.GamesShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GamesShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamesShopApplication.class, args);
	}

}
