package com.chzu.ice.chat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTConfig {
    @Value("${chat.config.jwt.access-token-expiration-time}")
    private long AccessTokenExpirationTime;

    @Value("${chat.config.jwt.refresh-token-expiration-time}")
    private long RefreshTokenExpirationTime;

    @Value("${chat.config.jwt.issuer}")
    private String issuer;


    public long getAccessTokenExpirationTime() {
        return AccessTokenExpirationTime;
    }

    public long getRefreshTokenExpirationTime() {
        return RefreshTokenExpirationTime;
    }

    public String getIssuer() {
        return issuer;
    }
}
