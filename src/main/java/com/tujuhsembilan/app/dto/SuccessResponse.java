package com.tujuhsembilan.app.dto;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponse<T>{
    
    private String message;
    
    private HttpStatus status;
    
    @Nullable
    private T data;

    private Integer size;

    private Integer page;
}
