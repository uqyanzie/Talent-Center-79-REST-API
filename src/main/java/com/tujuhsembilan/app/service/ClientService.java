package com.tujuhsembilan.app.service;

import java.util.List;

import com.tujuhsembilan.app.dto.client.ClientPositionResponse;

public interface ClientService {
    
    List<ClientPositionResponse> getClientPositionList();
}
