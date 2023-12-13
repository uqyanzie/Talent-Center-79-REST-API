package com.tujuhsembilan.app.dto;

import java.util.List;

import com.tujuhsembilan.app.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    
    private String firstName;
    private String lastName;
    private String email;

    public static List<UserResponse> fromEntityList(List<User> users) {
        
        return users.stream().map(user -> {
            return UserResponse.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .build();
        }).toList();
    }
}
