package org.sdl.mintyn_bank_test.controller;

import org.sdl.mintyn_bank_test.dto.*;
import org.sdl.mintyn_bank_test.exception.UserExistException;
import org.sdl.mintyn_bank_test.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userService;


    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @PostMapping
    ResponseEntity<ApiResponse<CreateUserResponse>> createUser(@RequestBody CreateUserRequest userRequest){
        try {
            userService.checkIfUserExistsByEmail(userRequest.email());
            var userResponse = userService.createUser(userRequest);
            var successResponse = new ApiResponse<>(userResponse,
                    HttpStatus.OK, true);
            return ResponseEntity.ok(successResponse);
        } catch (UserExistException e) {
            var errorResponse = new ApiResponse<>(new CreateUserResponse(e.getMessage(), null),
                            HttpStatus.CONFLICT, false);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }


}
