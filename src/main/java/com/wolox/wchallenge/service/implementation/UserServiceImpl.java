/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.service.implementation;

import com.wolox.wchallenge.dto.UserDTO;
import com.wolox.wchallenge.service.UserService;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Daniela
 */
@Service
public class UserServiceImpl implements UserService {

    public static final String USERS_URL = "https://jsonplaceholder.typicode.com/users";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<UserDTO> getUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        List<UserDTO> response = Arrays.asList(restTemplate
                .exchange(USERS_URL, HttpMethod.GET, entity, UserDTO[].class).getBody());
        return response;
    }

    @Override
    public UserDTO getUserById(String userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String urlAux = USERS_URL + "/" + userId;
        UserDTO response = restTemplate
                .exchange(urlAux, HttpMethod.GET, entity, UserDTO.class).getBody();
        return response;
    }

}
