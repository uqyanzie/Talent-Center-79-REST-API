package com.tujuhsembilan.app.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.tujuhsembilan.app.dto.UserResponse;

public interface UserService {

    UserDetailsService userDetailsService();
    
    List<UserResponse> getAllUsers();
  
} 
