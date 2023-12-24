package org.sdl.mintyn_bank_test.exception;

public class UserExistException extends RuntimeException{

    public UserExistException(String email) {
        super("User with email " + email + " already exists.");
    }
}
