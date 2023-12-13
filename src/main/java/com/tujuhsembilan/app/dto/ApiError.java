package com.tujuhsembilan.app.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiError {

   private HttpStatus status;
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
   private LocalDateTime timestamp;
   private String message;
   private String debugMessage;
   private List<ApiSubError> subErrors;
   private Throwable exception;

   private ApiError() {
       timestamp = LocalDateTime.now();
   }

   public ApiError(HttpStatus status) {
       this();
       this.status = status;
   }

   public ApiError(HttpStatus status, Throwable ex) {
       this();
       this.status = status;
       this.exception = ex;
   }

   public ApiError(HttpStatus status, Throwable ex, String message) {
       this();
       this.status = status;
       this.message = message;
       this.exception = ex;
       this.debugMessage = ex.getLocalizedMessage();
   }
}