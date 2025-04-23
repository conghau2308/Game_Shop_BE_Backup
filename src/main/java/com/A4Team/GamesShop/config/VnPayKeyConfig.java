package com.A4Team.GamesShop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VnPayKeyConfig {

    @Value("${payment.vnpay.tmncode}")
    private String vnpTmnCode;

    @Value("${payment.vnpay.secretkey}")
    private String vnpHashSecret;

    public String getVnpTmnCode() {
        return vnpTmnCode;
    }

    public String getVnpHashSecret() {
        return vnpHashSecret;
    }
}
