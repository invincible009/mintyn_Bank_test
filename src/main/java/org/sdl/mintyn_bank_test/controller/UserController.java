package org.sdl.mintyn_bank_test.controller;

import jakarta.websocket.server.PathParam;
import org.sdl.mintyn_bank_test.dto.*;
import org.sdl.mintyn_bank_test.dto.binlist.LookupResponse;
import org.sdl.mintyn_bank_test.exception.UserExistException;
import org.sdl.mintyn_bank_test.service.BinlistService;
import org.sdl.mintyn_bank_test.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userService;
    private final BinlistService binlistService;


    public UserController(UserServiceImpl userService, BinlistService binlistService) {
        this.userService = userService;
        this.binlistService = binlistService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<CreateUserResponse>> createUser(@RequestBody CreateUserRequest userRequest){
        try {
            userService.checkIfUserExistsByEmail(userRequest.email());
            var userResponse = userService.createUser(userRequest);
            var successResponse = new ApiResponse<>(true,userResponse);
            return ResponseEntity.ok(successResponse);
        } catch (UserExistException e) {
            var errorResponse = new ApiResponse<>(false,new CreateUserResponse(e.getMessage(), null));
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }

    @GetMapping("card-scheme/verify/{cardNumber}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<VerifyResponse>> cardLookup(@PathVariable("cardNumber")String cardNumber){
        try{
            var lookupResponse = binlistService.performLookup(cardNumber);
            var lookupResponseApiResponse = new ApiResponse<>(true,lookupResponse);
            return ResponseEntity.status(HttpStatus.OK).body(lookupResponseApiResponse);
        }catch (Exception exception){
            var errorResponse = new ApiResponse<>(false,VerifyResponse.builder().build());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }




}
