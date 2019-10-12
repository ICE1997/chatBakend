package com.chzu.ice.chat.util;


import com.chzu.ice.chat.config.JWTConfig;
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

@Component
public class JavaTokenUtil implements Serializable {
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final long EXPIRATION_TIME = 432000000;
    private static final String SECRET = "haha";

    @Autowired
    private JWTConfig jwtConfig;

    public String generateToken(UserDetails userDetails, long exp) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        return Jwts.builder().setClaims(claims)
                .setExpiration(new Date(Instant.now().toEpochMilli() + exp))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public String generateAccessToken(UserDetails userDetails) {
        return generateToken(userDetails, jwtConfig.getAccessTokenExpirationTime());
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return generateToken(userDetails, jwtConfig.getRefreshTokenExpirationTime());
    }

    public Boolean validate(String token, UserDetails userDetails) {
        Principal principal = (Principal) userDetails;
        String username = getUsernameFromToken(token);
        return (username.equals(principal.getUsername()) && !isTokenExpired(token));
    }

    public Boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }


    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    public Claims getClaimsFromToken(String token) {
        System.out.println(Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody());
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
