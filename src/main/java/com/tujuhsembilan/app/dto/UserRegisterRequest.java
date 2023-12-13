package com.tujuhsembilan.app.dto;

import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tujuhsembilan.app.model.ClientPosition;

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
public class UserRegisterRequest {

    @NotBlank
    @Size(min = 1, max = 100)
    private String firstName;
    
    @NotBlank
    @Size(min = 1, max = 100)
    private String lastName;
    
    @NotBlank
    @Size(min = 1, max = 100)
    private String email;

    @NotBlank
    @Size(min = 8, max = 50)
    private String password;
    
    @NotBlank
    @Size(min = 8, max = 50)
    private String confirmPassword;

    private UUID clientPositionId;

    @NotBlank
    private String gender;

    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Timestamp birthDate;

    @NotBlank
    @Size(min = 1, max = 100)
    private String agencyName;

    @NotBlank
    @Size(min = 1, max = 255)
    private String agencyAddress;
    
}
