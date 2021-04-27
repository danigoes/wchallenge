/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.service.implementation;

import com.wolox.wchallenge.dto.AlbumDTO;
import com.wolox.wchallenge.service.AlbumService;
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
public class AlbumServiceImpl implements AlbumService {

    private static final String ALBUMS_URL = "https://jsonplaceholder.typicode.com/albums";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<AlbumDTO> getAlbums() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        List<AlbumDTO> response = Arrays.asList(restTemplate
                .exchange(ALBUMS_URL, HttpMethod.GET, entity, AlbumDTO[].class).getBody());
        return response;
    }

    @Override
    public List<AlbumDTO> getAlbumsByUserId(String userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String urlAux = ALBUMS_URL + "?userId=" + userId;
        List<AlbumDTO> response = Arrays.asList(restTemplate
                .exchange(urlAux, HttpMethod.GET, entity, AlbumDTO[].class).getBody());
        return response;
    }

    @Override
    public AlbumDTO getAlbumById(String albumId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String urlAux = ALBUMS_URL + "/" + albumId;
        AlbumDTO response = restTemplate
                .exchange(urlAux, HttpMethod.GET, entity, AlbumDTO.class).getBody();
        return response;
    }

}
