package org.sdl.mintyn_bank_test.service;

import org.sdl.mintyn_bank_test.dto.CreateUserRequest;
import org.sdl.mintyn_bank_test.dto.CreateUserResponse;
import org.sdl.mintyn_bank_test.dto.LoginUserRequest;
import org.sdl.mintyn_bank_test.dto.LoginUserResponse;

public interface UserService {
    CreateUserResponse createUser(CreateUserRequest request);
    void checkIfUserExistsByEmail(String email);
}
