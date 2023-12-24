package org.sdl.mintyn_bank_test.dto;

import org.sdl.mintyn_bank_test.entity.Authority;

import java.util.List;

public record CreateUserRequest(String firstname,
                                String lastname,
                                String email,
                                String password,
                                String username,
                                List<Authority> authorities) {
}
