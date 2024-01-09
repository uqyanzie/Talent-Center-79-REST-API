package com.tujuhsembilan.app.service;

import com.tujuhsembilan.app.dto.auth.JwtAuthResponse;
import com.tujuhsembilan.app.dto.auth.RefreshTokenRequest;
import com.tujuhsembilan.app.dto.auth.UserRegisterRequest;
import com.tujuhsembilan.app.dto.auth.UserRegisterResponse;
import com.tujuhsembilan.app.dto.auth.UserSigninRequest;

public interface AuthenticationService {
    
    UserRegisterResponse register(UserRegisterRequest request);

    JwtAuthResponse signin(UserSigninRequest request);

    JwtAuthResponse refresh(RefreshTokenRequest request);
} 
