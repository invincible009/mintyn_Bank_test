package org.sdl.mintyn_bank_test.service;


import org.sdl.mintyn_bank_test.dto.LoginUserResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {


    @Override
    public LoginUserResponse generateToken(String username, String password) {

        //SecurityContextHolder.getContext().setAuthentication(authentication);

        // TODO: Generate and return JWT token here
        // Example using JJWT library:
        // String token = JwtUtils.generateToken(username);

        return new LoginUserResponse("", "");
    }
}
