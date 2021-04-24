/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.service.implementation;

import com.wolox.wchallenge.dto.PhotoDTO;
import com.wolox.wchallenge.service.PhotoService;
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
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private RestTemplate restTemplate;
    
    private static final String PHOTOS_URL = "https://jsonplaceholder.typicode.com/photos";
    
    @Override
    public List<PhotoDTO> getPhotos() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        List<PhotoDTO> response = Arrays.asList(restTemplate.exchange(PHOTOS_URL, HttpMethod.GET, entity, PhotoDTO[].class).getBody());
        return response;
    }
    
}
