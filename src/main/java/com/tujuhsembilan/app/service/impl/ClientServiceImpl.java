package com.tujuhsembilan.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tujuhsembilan.app.dto.client.ClientPositionResponse;
import com.tujuhsembilan.app.model.ClientPosition;
import com.tujuhsembilan.app.repository.ClientPositionRepository;
import com.tujuhsembilan.app.service.ClientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientServiceImpl implements ClientService{
    
    private final ClientPositionRepository clientPositionRepository;

    public List<ClientPositionResponse> getClientPositionList() {

        List<ClientPosition> clientPositions = clientPositionRepository.findAll();

        return ClientPositionResponse.fromEntityList(clientPositions);
    }
}
