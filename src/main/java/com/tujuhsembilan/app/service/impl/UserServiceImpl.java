package com.tujuhsembilan.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tujuhsembilan.app.dto.UserResponse;
import com.tujuhsembilan.app.model.User;
import com.tujuhsembilan.app.repository.ClientPositionRepository;
import com.tujuhsembilan.app.repository.ClientRepository;
import com.tujuhsembilan.app.repository.RoleRepository;
import com.tujuhsembilan.app.repository.UserRepository;
import com.tujuhsembilan.app.service.UserService;
import com.tujuhsembilan.app.service.ValidationService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    
    private Throwable throwable;
    
    private ValidationService validator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientPositionRepository clientPositionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService(){
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };  
    }
    
    @Transactional
    public List<UserResponse> getAllUsers() {

        List<User> user = userRepository.findAll();

        return UserResponse.fromEntityList(user);
    }
}
