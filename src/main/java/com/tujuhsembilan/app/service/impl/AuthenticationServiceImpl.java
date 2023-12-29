package com.tujuhsembilan.app.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import com.tujuhsembilan.app.dto.auth.JwtAuthResponse;
import com.tujuhsembilan.app.dto.auth.RefreshTokenRequest;
import com.tujuhsembilan.app.dto.auth.UserRegisterRequest;
import com.tujuhsembilan.app.dto.auth.UserRegisterResponse;
import com.tujuhsembilan.app.dto.auth.UserSigninRequest;
import com.tujuhsembilan.app.model.Client;
import com.tujuhsembilan.app.model.Role;
import com.tujuhsembilan.app.model.User;
import com.tujuhsembilan.app.repository.ClientPositionRepository;
import com.tujuhsembilan.app.repository.ClientRepository;
import com.tujuhsembilan.app.repository.RoleRepository;
import com.tujuhsembilan.app.repository.UserRepository;
import com.tujuhsembilan.app.service.AuthenticationService;
import com.tujuhsembilan.app.service.JWTService;
import com.tujuhsembilan.app.service.ValidationService;

import jakarta.transaction.Transactional;
import lib.security.BCrypt;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
    
    @Autowired
    private ValidationService validator;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ClientRepository clientRepository;

    @Autowired
    private final ClientPositionRepository clientPositionRepository;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final JWTService jwtService;

    @Transactional
    public UserRegisterResponse register(UserRegisterRequest request) {
        validator.validate(request);

        if (!request.getPassword().equals(request.getConfirmPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password and Confirm Password must be same!");
        }

        if (userRepository.existsByEmail(request.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email Already Registered!");
        }

        Role role = roleRepository.findByRoleName("Client");
        
        User user = new User();

        Client client = new Client();

        user.setUserId(UUID.randomUUID());
        user.setRole(role);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getFirstName()+request.getLastName()+"#"+user.getUserId().toString().substring(0, 4).toLowerCase());
        user.setEmail(request.getEmail());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setCreatedTime(Timestamp.valueOf(java.time.LocalDateTime.now()));
        user.setLastModifiedTime(Timestamp.valueOf(java.time.LocalDateTime.now()));
        user.setCreatedBy("Admin");
        user.setLastModifiedBy("Admin");

        userRepository.save(user);

        client.setClientId(UUID.randomUUID());
        client.setCreatedTime(user.getCreatedTime());
        client.setLastModifiedTime(user.getLastModifiedTime());
        client.setCreatedBy(user.getCreatedBy());
        client.setLastModifiedBy(user.getLastModifiedBy());
        client.setClientName(StringUtils.capitalize(request.getFirstName() + " " + request.getLastName()));
        client.setClientPosition(clientPositionRepository.findById(request.getClientPositionId()).orElse(null));
        client.setGender(request.getGender());
        client.setBirthDate(request.getBirthDate());
        client.setAgencyName(request.getAgencyName());
        client.setAgencyAddress(request.getAgencyAddress());
        client.setClientId(UUID.randomUUID());
        client.setEmail(request.getEmail());
        client.setUser(user);
        client.setIsActive(true);

        clientRepository.save(client);

        return UserRegisterResponse.builder()
            .userId(user.getUserId().toString())
            .clientId(client.getClientId().toString())
            .roleId(user.getRole().getRoleId().toString())
            .clientPositionId(client.getClientPosition().getClientPositionId().toString())
            .email(user.getEmail())
            .username(user.getUsername())
            .createdAt(user.getCreatedTime().toString())
            .build(); 
    }

    @Transactional
    public JwtAuthResponse signin(UserSigninRequest request){
        validator.validate(request);

        
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password!"));

        var jwt = jwtService.generateToken(user);

        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthResponse response = new JwtAuthResponse();

        response.setEmail(user.getEmail());
        response.setUserId(user.getUserId().toString());
        response.setClientId(clientRepository.findByUser(user).getClientId().toString());
        response.setToken(jwt);
        response.setRoleId(user.getRole().getRoleId().toString());
        response.setRefreshToken(refreshToken);

        return response;
    }

    @Transactional
    public JwtAuthResponse refresh(RefreshTokenRequest request){
        String userEmail = jwtService.extractUsername(request.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();

        if (jwtService.isTokenValid(request.getToken(), user)){
            var jwt = jwtService.generateToken(user);   

            JwtAuthResponse response = new JwtAuthResponse();

            response.setToken(request.getToken());

            return response;
        }

        return null;

    }

}
