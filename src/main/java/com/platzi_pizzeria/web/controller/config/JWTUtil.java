package com.platzi_pizzeria.web.controller.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JWTUtil {

    private static String SECRET_KEY = "pl4tzi_p1zz3r4";
    private static Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);


    public String create(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuer("platzi_pizzeria")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
                .sign(ALGORITHM);
    }
}
