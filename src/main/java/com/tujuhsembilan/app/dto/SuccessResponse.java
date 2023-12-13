package com.tujuhsembilan.app.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class SuccessResponse<T>{
    
    private String message;
    
    private HttpStatus status;
    
    private T data;
}
