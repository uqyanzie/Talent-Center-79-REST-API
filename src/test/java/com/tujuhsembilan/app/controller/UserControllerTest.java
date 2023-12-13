package com.tujuhsembilan.app.controller;

import java.sql.Timestamp;
import java.util.UUID;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.tujuhsembilan.app.dto.UserRegisterRequest;
import com.tujuhsembilan.app.dto.UserRegisterResponse;
import com.tujuhsembilan.app.dto.WebResponse;
import com.tujuhsembilan.app.repository.ClientPositionRepository;
import com.tujuhsembilan.app.repository.ClientRepository;
import com.tujuhsembilan.app.repository.RoleRepository;
import com.tujuhsembilan.app.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientPositionRepository clientPositionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRegisterSuccess() {

            UserRegisterRequest request = new UserRegisterRequest();
            request.setFirstName("John");
            request.setLastName("Doe");
            request.setEmail("johndoe@testing.com");
            request.setPassword("johndoe123");
            request.setConfirmPassword("johndoe123");
            request.setAgencyName("Padepokan Tujuh Sembilan");
            request.setAgencyAddress("Gg. Terasana No.79 Cihampelas");
            request.setBirthDate(Timestamp.valueOf("2002-04-12 00:00:00.0"));
            request.setClientPositionId(UUID.fromString("22900d4a-1e68-4d09-8ef4-eb99614907d5"));
            request.setGender("Male");

            try {
                
                String response = mockMvc.perform(post("/user-management/users/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

                WebResponse<UserRegisterResponse> webResponse = objectMapper.readValue(response, new TypeReference<WebResponse<UserRegisterResponse>>() {});

                assertNotNull(webResponse.getData());
                assertNotNull(webResponse.getData().getClientId());
                assertNotNull(webResponse.getData().getClientPositionId());
                assertNotNull(webResponse.getData().getUsername());
                assertEquals(webResponse.getData().getEmail(), request.getEmail());
                assertNotNull(webResponse.getData().getRoleId());
                assertNull(webResponse.getErrors());

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
}
