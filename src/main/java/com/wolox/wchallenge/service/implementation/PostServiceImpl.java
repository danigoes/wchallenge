/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.service.implementation;

import com.wolox.wchallenge.dto.PostDTO;
import com.wolox.wchallenge.service.PostService;
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
public class PostServiceImpl implements PostService {

    private static final String POSTS_URL = "https://jsonplaceholder.typicode.com/posts";
    private static final ParameterizedTypeReference<List<PostDTO>> typeRef = 
                new ParameterizedTypeReference<List<PostDTO>>() {};

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<PostDTO> getPostsByUserId(String userId) {
        log.info("Getting posts by user id");
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = POSTS_URL + "?userId=" + userId;
        ResponseEntity<List<PostDTO>> response = restTemplate
                .exchange(url, HttpMethod.GET, entity, typeRef);
        return response.getBody();
    }

}
