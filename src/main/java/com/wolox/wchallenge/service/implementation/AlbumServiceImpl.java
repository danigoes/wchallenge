/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.service.implementation;

import com.wolox.wchallenge.dto.AlbumDTO;
import com.wolox.wchallenge.service.AlbumService;
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
public class AlbumServiceImpl implements AlbumService {

    private static final String ALBUMS_URL = "https://jsonplaceholder.typicode.com/albums";
    private static final ParameterizedTypeReference<List<AlbumDTO>> typeRef = 
                new ParameterizedTypeReference<List<AlbumDTO>>(){};

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<AlbumDTO> getAlbums() {
        log.info("Getting all albums");
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<AlbumDTO>> response = restTemplate
                .exchange(ALBUMS_URL, HttpMethod.GET, entity, typeRef);
        return response.getBody();
    }

    @Override
    public List<AlbumDTO> getAlbumsByUserId(String userId) {
        log.info("Getting all albums by user id");
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String urlAux = ALBUMS_URL + "?userId=" + userId;
        ResponseEntity<List<AlbumDTO>> response = restTemplate
                .exchange(urlAux, HttpMethod.GET, entity, typeRef);
        return response.getBody();
    }

    @Override
    public AlbumDTO getAlbumById(String albumId) {
        log.info("Getting all albums by album id");
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String urlAux = ALBUMS_URL + "/" + albumId;
        ResponseEntity<AlbumDTO> response = restTemplate
                .exchange(urlAux, HttpMethod.GET, entity, AlbumDTO.class);
        return response.getBody();
    }

}
