package com.tujuhsembilan.app.dto;

import org.springframework.http.HttpStatus;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class SuccessResponse<T>{
    
    private String message;
    
    private HttpStatus status;
    
    @Nullable
    private T data;
}
