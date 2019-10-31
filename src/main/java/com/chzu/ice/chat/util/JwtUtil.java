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

/**
 * @author mason
 */
@Component
public class JavaTokenUtil implements Serializable {
    @Autowired
    private JwtConfig jwtConfig;

    private String generateToken(UserDetails userDetails, long exp) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuer(jwtConfig.getIssuer())
                .setIssuedAt(new Date(Instant.now().toEpochMilli()))
                .setExpiration(new Date(Instant.now().toEpochMilli() + exp))
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
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

    private Boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }


    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    private Claims getClaimsFromToken(String token) {
        System.out.println(Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(token).getBody());
        return Jwts.parser()
                .setSigningKey(jwtConfig.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }
}
