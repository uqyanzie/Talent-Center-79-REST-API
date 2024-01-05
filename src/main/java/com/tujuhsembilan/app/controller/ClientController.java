package com.tujuhsembilan.app.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tujuhsembilan.app.dto.SuccessResponse;
import com.tujuhsembilan.app.dto.client.ClientPositionResponse;
import com.tujuhsembilan.app.service.ClientService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/api/master-management/client-position-option-lists")
    public ResponseEntity<SuccessResponse<List<ClientPositionResponse>>> getClientPositionList() {
        try{
            List<ClientPositionResponse> response = clientService.getClientPositionList();
            
            return ResponseEntity.<SuccessResponse<List<ClientPositionResponse>>>ok(
                SuccessResponse.<List<ClientPositionResponse>>builder()
                    .message(String.format("Retrieved total of %d client position", response.size()))
                    .status(HttpStatus.OK)
                    .data(response)
                    .build()
            );
        }
        catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex);
        }   
    }
    
}
