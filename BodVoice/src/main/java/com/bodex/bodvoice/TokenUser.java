package com.bodex.bodvoice;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


public class TokenUser {
    //chwilowo nie dziala ale bedzie kiedys dzialac XD
    //chwilowo nie dziala ale bedzie kiedys dzialac XD
    //chwilowo nie dziala ale bedzie kiedys dzialac XD
    //chwilowo nie dziala ale bedzie kiedys dzialac XD
    //chwilowo nie dziala ale bedzie kiedys dzialac XD
    //chwilowo nie dziala ale bedzie kiedys dzialac XD
    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
    private static final String SECRET_KEY = "huj";
    private String token;

    public String generateNewToken() {
        Key key = generateKey();

        token = Jwts.builder()
                .setSubject("userId")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    public String getToken(){
        return token;
    }

    private static Key generateKey(){
        byte[] apiKeySecretBytes = new byte[256];
        new SecureRandom().nextBytes(apiKeySecretBytes);
        return io.jsonwebtoken.security.Keys.hmacShaKeyFor(apiKeySecretBytes);
    }

    private static Key getKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public static String getUsernameFromToken(String token){
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        return body.getSubject();
    }
}
