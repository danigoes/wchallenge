/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.service.implementation;

import com.wolox.wchallenge.dto.AlbumDTO;
import com.wolox.wchallenge.dto.PhotoDTO;
import com.wolox.wchallenge.service.AlbumService;
import com.wolox.wchallenge.service.PhotoService;
import java.util.ArrayList;
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
public class PhotoServiceImpl implements PhotoService {

    private static final String PHOTOS_URL = "https://jsonplaceholder.typicode.com/photos";
    private static final ParameterizedTypeReference<List<PhotoDTO>> typeRef = 
                new ParameterizedTypeReference<List<PhotoDTO>>() {};
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AlbumService albumService;

    @Override
    public List<PhotoDTO> getPhotos() {
        log.info("Getting photos");
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<PhotoDTO>> response = restTemplate
                .exchange(PHOTOS_URL, HttpMethod.GET, entity, typeRef);
        return response.getBody();
    }

    @Override
    public List<PhotoDTO> getPhotosByAlbumId(String albumId) {
        log.info("Getting photos by album id");
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String urlAux = PHOTOS_URL + "?albumId=" + albumId;
        ResponseEntity<List<PhotoDTO>> response = restTemplate
                .exchange(urlAux, HttpMethod.GET, entity, typeRef);
        return response.getBody();
    }

    @Override
    public List<PhotoDTO> getPhotosByUserId(String userId) {
        log.info("Getting photos by user id");
        List<PhotoDTO> response = new ArrayList();
        List<AlbumDTO> albums = albumService.getAlbumsByUserId(userId);
        albums.stream().map((album) -> getPhotosByAlbumId(album.getId().toString()))
                .forEachOrdered((photos) -> {
                    photos.forEach((photo) -> {
                        response.add(photo);
                    });
                });
        return response;
    }

}
