package org.sdl.mintyn_bank_test.controller;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;
import org.sdl.mintyn_bank_test.dto.*;
import org.sdl.mintyn_bank_test.exception.UserExistException;
import org.sdl.mintyn_bank_test.service.BinlistService;
import org.sdl.mintyn_bank_test.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/api/")
public class UserController {
    private final UserServiceImpl userService;
    private final BinlistService binlistService;


    public UserController(UserServiceImpl userService, BinlistService binlistService) {
        this.userService = userService;
        this.binlistService = binlistService;
    }


    @PostMapping("user/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<?>> createUser(@RequestBody CreateUserRequest userRequest){
        try {
            userService.checkIfUserExistsByEmail(userRequest.email());
            var userResponse = userService.createUser(userRequest);
            var successResponse = ApiResponse.builder().payload(userResponse).success(true).build();
            return ResponseEntity.ok(successResponse);
        } catch (UserExistException e) {
            var errorResponse = ApiResponse.builder()
                    .message(e.getMessage())
                    .success(false)
                    .build();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }

    @GetMapping("card-scheme/verify/{cardNumber}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<?>> cardLookup(@PathVariable("cardNumber")String cardNumber){
        try{
            var lookupResponse = binlistService.performLookup(cardNumber);
            var lookupResponseApiResponse = ApiResponse.builder().payload(lookupResponse).success(true).build();
            return ResponseEntity.status(HttpStatus.OK).body(lookupResponseApiResponse);
        }catch (Exception exception){
            var errorResponse = ApiResponse.builder().message(exception.getMessage()).success(false).build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("card-scheme/stats")
    public ResponseEntity<?> getCardSchemeStats(
            @RequestParam(name = "start", defaultValue = "1") int start,
            @RequestParam(name = "limit", defaultValue = "3") int limit,
            HttpServletRequest request){

        try{
            String clientId = getClientId(request);
            boolean allowed = binlistService.isAllowed(clientId, start, limit);

            if (allowed) {
                return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                        .payload(binlistService.getCardInfo())
                        .size(133)
                        .limit(limit)
                        .start(start)
                        .success(true)
                        .build());
            } else {
                return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(ApiResponse.builder()
                        .message("Too many Hits! Please try again later")
                        .success(true)
                        .build());
            }

        }catch (Exception exception){
            var errorResponse = ApiResponse.builder()
                    .message(exception.getMessage())
                    .success(false)
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    private String getClientId(HttpServletRequest request) {
        return request.getRemoteAddr();
    }



}
