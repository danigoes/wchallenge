/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.service.implementation;

import com.wolox.wchallenge.dto.AlbumDTO;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Daniela
 */
@RunWith(SpringRunner.class)
public class AlbumServiceImplTest {
    
    private static final String ALBUMS_URL = "https://jsonplaceholder.typicode.com/albums";
    
    @Mock
    private RestTemplate restTemplate;
    
    @InjectMocks
    private AlbumServiceImpl albumService;
    
    @Test
    public void getAlbums_ReturnOK() {
        List<AlbumDTO> albumArray = new ArrayList();
        AlbumDTO album = new AlbumDTO(1, 1, "album test");
        albumArray.add(album);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        ParameterizedTypeReference<List<AlbumDTO>> typeRef = new ParameterizedTypeReference<List<AlbumDTO>>() {};
        ResponseEntity<List<AlbumDTO>> myEntity = new ResponseEntity<>(albumArray, HttpStatus.OK);
        when(restTemplate
                .exchange(ALBUMS_URL, HttpMethod.GET, entity, typeRef))
                .thenReturn(myEntity);
        
        List<AlbumDTO> response = albumService.getAlbums();
        
        assertEquals(albumArray.get(0).getId(), response.get(0).getId());
    }
    
    @Test
    public void getAlbumsByUserId_ReturnOK() {
        String userId = "1";
        List<AlbumDTO> albums = new ArrayList();
        AlbumDTO album = new AlbumDTO(1, 1, "album test");
        AlbumDTO album2 = new AlbumDTO(2, 2, "album test 2");
        albums.add(album);
        albums.add(album2);
        
        String url = ALBUMS_URL + "?userId=" + userId;
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        ParameterizedTypeReference<List<AlbumDTO>> typeRef = new ParameterizedTypeReference<List<AlbumDTO>>() {};
        ResponseEntity<List<AlbumDTO>> myEntity = new ResponseEntity<>(albums, HttpStatus.OK);
        when(restTemplate
                .exchange(url, HttpMethod.GET, entity, typeRef))
                .thenReturn(myEntity);
        
        List<AlbumDTO> response = albumService.getAlbumsByUserId(userId);
        
        assertEquals(albums.get(0).getId(), response.get(0).getId());
    }

    @Test
    public void getAlbumById_ReturnOK() {
        String albumId = "1";
        AlbumDTO album = new AlbumDTO(1, 1, "album test");
        
        String url = ALBUMS_URL + "/" + albumId;
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<AlbumDTO> myEntity = new ResponseEntity<>(album, HttpStatus.OK);
        when(restTemplate
                .exchange(url, HttpMethod.GET, entity, AlbumDTO.class))
                .thenReturn(myEntity);
        
        AlbumDTO response = albumService.getAlbumById(albumId);
        
        assertEquals(album.getId(), response.getId());
    }
}
