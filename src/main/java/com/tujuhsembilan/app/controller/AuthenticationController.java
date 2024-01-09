package com.tujuhsembilan.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tujuhsembilan.app.dto.SuccessResponse;
import com.tujuhsembilan.app.dto.auth.JwtAuthResponse;
import com.tujuhsembilan.app.dto.auth.RefreshTokenRequest;
import com.tujuhsembilan.app.dto.auth.UserRegisterRequest;
import com.tujuhsembilan.app.dto.auth.UserRegisterResponse;
import com.tujuhsembilan.app.dto.auth.UserSigninRequest;
import com.tujuhsembilan.app.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {
    
    private final AuthenticationService authenticationService;

   @PostMapping(
        path = "/api/user-management/users/register",
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<SuccessResponse<UserRegisterResponse>> register(@RequestBody UserRegisterRequest request) {
            
            try {
                UserRegisterResponse response = authenticationService.register(request);
                
                return ResponseEntity.<SuccessResponse<UserRegisterResponse>>ok(
                    SuccessResponse.<UserRegisterResponse>builder()
                        .message("User registered successfully")
                        .status(HttpStatus.CREATED)
                        .data(response)
                        .build()
                );
            } catch (Exception ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex);
            }
    }

    @PostMapping(
        path = "/api/user-management/users/sign-in",
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<SuccessResponse<JwtAuthResponse>> signin(@RequestBody UserSigninRequest request){
        try {
                JwtAuthResponse response = authenticationService.signin(request);
                
                return ResponseEntity.<SuccessResponse<JwtAuthResponse>>ok(
                    SuccessResponse.<JwtAuthResponse>builder()
                        .message("User successfully authenticated")
                        .status(HttpStatus.OK)
                        .data(response)
                        .build()
                );
            } catch (Exception ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex);
            }
    }

    @PostMapping("/api/refresh-token")
    public ResponseEntity<JwtAuthResponse> refresh(@RequestBody RefreshTokenRequest request) {
        
        return ResponseEntity.ok(authenticationService.refresh(request));
    
    }
    
}
