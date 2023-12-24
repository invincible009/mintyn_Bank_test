package org.sdl.mintyn_bank_test.controller;


import org.sdl.mintyn_bank_test.dto.ApiResponse;
import org.sdl.mintyn_bank_test.dto.LoginUserRequest;
import org.sdl.mintyn_bank_test.dto.LoginUserResponse;
import org.sdl.mintyn_bank_test.service.AuthServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {


    private final AuthServiceImpl authenticationService;

    public AuthController(AuthServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginUserResponse>> login(@RequestBody LoginUserRequest loginRequest) {
        var loginUserResponse = authenticationService.generateToken(loginRequest.username(), loginRequest.password());
        return ResponseEntity.ok(new ApiResponse<>(loginUserResponse, HttpStatus.OK, true));
    }
}
