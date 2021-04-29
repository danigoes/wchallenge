/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.service.implementation;

import com.wolox.wchallenge.dto.UserDTO;
import com.wolox.wchallenge.service.UserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Daniela
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private static final String USERS_URL = "https://jsonplaceholder.typicode.com/users";
    private static final ParameterizedTypeReference<List<UserDTO>> typeRef = 
                new ParameterizedTypeReference<List<UserDTO>>() {};

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<UserDTO> getUsers() {
        log.info("Getting users");
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<UserDTO>> response = restTemplate
                .exchange(USERS_URL, HttpMethod.GET, entity, typeRef);
        return response.getBody();
    }

    @Override
    public UserDTO getUserById(String userId) {
        log.info("Getting users by id");
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String urlAux = USERS_URL + "/" + userId;
        ResponseEntity<UserDTO> response = restTemplate
                .exchange(urlAux, HttpMethod.GET, entity, UserDTO.class);
        return response.getBody();
    }

}
