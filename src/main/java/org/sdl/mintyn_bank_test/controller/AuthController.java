package org.sdl.mintyn_bank_test.controller;


import org.sdl.mintyn_bank_test.JwtUtil;
import org.sdl.mintyn_bank_test.dto.ApiResponse;
import org.sdl.mintyn_bank_test.dto.LoginUserRequest;
import org.sdl.mintyn_bank_test.dto.LoginUserResponse;
import org.sdl.mintyn_bank_test.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {


    private final AuthServiceImpl authenticationService;

    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthController(AuthServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginUserRequest loginRequest) {


        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.builder()
                    .message("Invalid username or password")
                    .build());
        }

        var userDetails = authenticationService.authenticateUser(loginRequest);
        String token = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(ApiResponse.builder().payload(token).success(true).build());
    }
}
