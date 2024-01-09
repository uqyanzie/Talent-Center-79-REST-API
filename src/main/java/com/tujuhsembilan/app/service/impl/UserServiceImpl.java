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

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__())
public class UserServiceImpl implements UserService{
    
    private final UserRepository userRepository;

    
    private final ClientRepository clientRepository;

    
    private final ClientPositionRepository clientPositionRepository;

    
    private final RoleRepository roleRepository;

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
