package com.example.app_fast_food.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;


@Service
public class JwtService {
   @Value("${security.token.expiration}")
    private long expiration;

   @Value("${security.token.secretKey}")

    private String secretKey;


    public String generateToken(String phoneNumber){
           Date now = new Date();
           Date expiration = Date.from(now.toInstant().plusSeconds(this.expiration));


           return Jwts.builder()
                   .subject(phoneNumber)
                   .issuedAt(now)
                   .expiration(expiration)
                   .signWith(signingKey())
                   .compact();
    }

    private SecretKey signingKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey));
    }

    public Claims claims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
