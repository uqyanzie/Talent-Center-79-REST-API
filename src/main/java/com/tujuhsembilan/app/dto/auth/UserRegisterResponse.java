package com.tujuhsembilan.app.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterResponse {

    private String userId;

    private String roleId;

    private String clientId;

    private String username;

    private String email;

    private String clientPositionId;

    private String createdAt;

}
