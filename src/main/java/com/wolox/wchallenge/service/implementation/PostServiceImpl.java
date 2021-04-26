/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.service.implementation;

import com.wolox.wchallenge.dto.PostDTO;
import com.wolox.wchallenge.service.PostService;
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
public class PostServiceImpl implements PostService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    public static final String POSTS_URL = "https://jsonplaceholder.typicode.com/posts";//"https://jsonplaceholder.cypress.io/users";//

    @Override
    public List<PostDTO> getPostsByUserId(String userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        List<PostDTO> response = Arrays.asList(restTemplate.exchange(POSTS_URL + "?userId=" + userId, HttpMethod.GET, entity, PostDTO[].class).getBody());
        return response;
    }
    
}
