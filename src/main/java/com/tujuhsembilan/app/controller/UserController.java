package com.tujuhsembilan.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tujuhsembilan.app.dto.SuccessResponse;
import com.tujuhsembilan.app.dto.UserResponse;
import com.tujuhsembilan.app.dto.WebResponse;
import com.tujuhsembilan.app.dto.auth.UserRegisterRequest;
import com.tujuhsembilan.app.dto.auth.UserRegisterResponse;
import com.tujuhsembilan.app.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("api/user-management/users/")
    public ResponseEntity<SuccessResponse<List<UserResponse>>>  getAllUsers() {

         try {
                List<UserResponse> response = userService.getAllUsers();
                
                return ResponseEntity.<SuccessResponse<List<UserResponse>>>ok(
                    SuccessResponse.<List<UserResponse>>builder()
                        .message(String.format("Retrieved total of %d users", response.size()))
                        .status(HttpStatus.OK)
                        .data(response)
                        .build()
                );
            } catch (Exception ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex);
            }        
    }
    
}
