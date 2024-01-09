package com.tujuhsembilan.app.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSigninRequest {
    
    @NotBlank
    @Email
    @Size(min = 1, max = 100)
    private String email;

    @NotBlank
    @Size(min = 1, max = 100)
    private String password;
}
