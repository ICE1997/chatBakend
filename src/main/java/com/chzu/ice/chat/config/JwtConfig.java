package com.chzu.ice.chat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author mason
 */
@Component
public class JwtConfig {
    @Value("${chat.config.jwt.access-token-expiration-time}")
    private long accessTokenExpirationTime;

    @Value("${chat.config.jwt.refresh-token-expiration-time}")
    private long refreshTokenExpirationTime;

    @Value("${chat.config.jwt.issuer}")
    private String issuer;

    @Value("chat.config.jwt.secret")
    private String secret;


    public long getAccessTokenExpirationTime() {
        return accessTokenExpirationTime;
    }

    public long getRefreshTokenExpirationTime() {
        return refreshTokenExpirationTime;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getSecret() {
        return secret;
    }
}
