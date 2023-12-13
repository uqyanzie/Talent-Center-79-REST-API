package com.tujuhsembilan.app.service;

import java.util.Set;
import java.util.UUID;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import com.tujuhsembilan.app.dto.UserRegisterRequest;
import com.tujuhsembilan.app.dto.UserRegisterResponse;
import com.tujuhsembilan.app.dto.UserResponse;
import com.tujuhsembilan.app.model.Client;
import com.tujuhsembilan.app.model.Role;
import com.tujuhsembilan.app.model.User;
import com.tujuhsembilan.app.repository.UserRepository;
import com.tujuhsembilan.app.repository.ClientPositionRepository;
import com.tujuhsembilan.app.repository.ClientRepository;
import com.tujuhsembilan.app.repository.RoleRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lib.security.BCrypt;

public class UserService {
    
    private Throwable throwable;
    
    @Autowired
    private ValidationService validator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientPositionRepository clientPositionRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Transactional
    public List<UserResponse> getAllUsers() {

        List<User> user = userRepository.findAll();

        return UserResponse.fromEntityList(user);
    }

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
        client.setClientPositionId(request.getClientPositionId());
        client.setGender(request.getGender());
        client.setBirthDate(request.getBirthDate());
        client.setAgencyName(request.getAgencyName());
        client.setAgencyAddress(request.getAgencyAddress());
        client.setClientId(UUID.randomUUID());
        client.setEmail(request.getEmail());
        client.setUser(null);
        client.setIsActive(true);

        clientRepository.save(client);

        return UserRegisterResponse.builder()
            .userId(user.getUserId().toString())
            .clientId(client.getClientId().toString())
            .roleId(user.getRole().getRoleId().toString())
            .clientPositionId(client.getClientPositionId().toString())
            .email(user.getEmail())
            .username(user.getUsername())
            .createdAt(user.getCreatedTime().toString())
            .build();
    }



}
