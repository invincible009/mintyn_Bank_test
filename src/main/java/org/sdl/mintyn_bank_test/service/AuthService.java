package org.sdl.mintyn_bank_test.service;

import org.sdl.mintyn_bank_test.dto.LoginUserResponse;

public interface AuthService {
    LoginUserResponse generateToken(String username, String password);
}
