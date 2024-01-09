package com.tujuhsembilan.app.dto.auth;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtAuthResponse {

    private String email;

    private String userId;

    private String clientId;

    private String roleId;

    private String token;

    private String refreshToken;
}
