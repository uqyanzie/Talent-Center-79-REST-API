package com.tujuhsembilan.app.dto.auth;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RefreshTokenRequest {
    private String token;
}
