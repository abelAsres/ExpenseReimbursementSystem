package com.revature.service;

import com.revature.model.User;
import io.javalin.http.UnauthorizedResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public class JWTService {
    //This will be a singleton to ensure that the server will have the same key
    //for all instances of the service across the application

    private static JWTService instance;

    private Key key;


    private JWTService(){
        byte[] sercret = "ThisIsARandomlyTypedStringButItReallyIsn'tIfYouThinkAboutIt".getBytes();
        key = Keys.hmacShaKeyFor(sercret);
    }

    public static JWTService getInstance(){
        if(instance == null){
            instance = new JWTService();
        }

        return instance;
    }

    //Signing a JWT with the key
    public String createJWT(User user){
        return Jwts.builder()
                .setSubject(user.getUserName())
                .claim("user_id",user.getId())
                .claim("user_role",user.getRoleId())
                .signWith(key)
                .compact();
    }

    //Validating a JWT with the key
    public Jws<Claims> parseJWT(String jwt){
        try{
            Jws<Claims> token = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
            return token;
        }catch(JwtException e) {
            throw new UnauthorizedResponse("This JWT is invalid");
        }
    }
}
