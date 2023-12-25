package org.sdl.mintyn_bank_test.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.sdl.mintyn_bank_test.dto.LoginUserRequest;
import org.sdl.mintyn_bank_test.dto.LoginUserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class AuthServiceImpl implements AuthService {

    @Value("${app.jwt.token.secret}")
    private static String SECRET_KEY;

    @Value("${app.jwt.token.validity.time}")
    private static long EXPIRATION_TIME;
    @Override
    public LoginUserResponse generateToken(String username) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);

        var tokenString = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();

        return new LoginUserResponse(username, tokenString);
    }

    @Override
    public UserDetails authenticateUser(LoginUserRequest loginRequest) {
        return null;
    }
}
