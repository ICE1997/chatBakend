package com.chzu.ice.chat.util;


import com.chzu.ice.chat.config.JwtConfig;
import com.chzu.ice.chat.pojo.bean.Principal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mason
 */
@Component
public class JwtUtil implements Serializable {
    @Autowired
    private JwtConfig jwtConfig;

    private String generateToken(UserDetails userDetails, String type, long exp) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.SUBJECT, userDetails.getUsername());
        claims.put(Claims.ISSUER, jwtConfig.getIssuer());
        claims.put(Claims.ISSUED_AT, new Date(Instant.now().toEpochMilli()));
        claims.put(Claims.EXPIRATION, new Date(Instant.now().toEpochMilli() + exp));
        claims.put("type", type);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
                .compact();
    }

    public String generateAccessToken(UserDetails userDetails) {
        return generateToken(userDetails, jwtConfig.getTypeAccessToken(), jwtConfig.getAccessTokenExpirationTime());
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return generateToken(userDetails, jwtConfig.getTypeRefreshToken(), jwtConfig.getRefreshTokenExpirationTime());
    }

    private Boolean validate(String token, UserDetails userDetails, String type) {
        if (type.equals(getTypeFromToken(token))) {
            Principal principal = (Principal) userDetails;
            String username = getUsernameFromToken(token);
            return (username.equals(principal.getUsername()) && !isTokenExpired(token));
        }
        return false;
    }

    public Boolean validateAccessToken(String token, UserDetails userDetails) {
        return validate(token,userDetails,jwtConfig.getTypeAccessToken());
    }

    public Boolean validateRefreshToken(String token,UserDetails userDetails) {
        return validate(token,userDetails,jwtConfig.getTypeRefreshToken());
    }

    private Boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    public String getTypeFromToken(String token) {
        return (String) getClaimsFromToken(token).get("type");
    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtConfig.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }
}
