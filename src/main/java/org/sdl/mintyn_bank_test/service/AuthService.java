package org.sdl.mintyn_bank_test.service;

import org.sdl.mintyn_bank_test.dto.LoginUserRequest;
import org.sdl.mintyn_bank_test.dto.LoginUserResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    LoginUserResponse generateToken(String username);

    UserDetails authenticateUser(LoginUserRequest loginRequest);
}
