/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.service.implementation;

import com.wolox.wchallenge.dto.AlbumDTO;
import com.wolox.wchallenge.dto.PhotoDTO;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
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
public class PhotoServiceImplTest {
    
    private static final String PHOTOS_URL = "https://jsonplaceholder.typicode.com/photos";

    @Mock
    private RestTemplate restTemplate;
    
    @Mock
    private AlbumServiceImpl albumService;
    
    @InjectMocks
    private PhotoServiceImpl photoService;
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void getPhotosTest() {
        List<PhotoDTO> photos = new ArrayList();
        PhotoDTO photo = new PhotoDTO(1, 1, "photo title", "photo url", "photo thumbnailUrl");
        photos.add(photo);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        ParameterizedTypeReference<List<PhotoDTO>> typeRef = new ParameterizedTypeReference<List<PhotoDTO>>() {};
        ResponseEntity<List<PhotoDTO>> myEntity = new ResponseEntity<>(photos, HttpStatus.OK);
        when(restTemplate
                .exchange(PHOTOS_URL, HttpMethod.GET, entity, typeRef))
                .thenReturn(myEntity);
        
        List<PhotoDTO> response = photoService.getPhotos();
        
        assertEquals(photos.get(0).getId(), response.get(0).getId());
    }
    
    @Test
    public void getPhotosByAlbumId() {
        String albumId = "1";
        List<PhotoDTO> photos = new ArrayList();
        PhotoDTO photo = new PhotoDTO(1, 1, "photo title", "photo url", "photo thumbnailUrl");
        photos.add(photo);
        
        String url = PHOTOS_URL + "?albumId=" + albumId;
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        ParameterizedTypeReference<List<PhotoDTO>> typeRef = new ParameterizedTypeReference<List<PhotoDTO>>() {};
        ResponseEntity<List<PhotoDTO>> myEntity = new ResponseEntity<>(photos, HttpStatus.OK);
        when(restTemplate
                .exchange(url, HttpMethod.GET, entity, typeRef))
                .thenReturn(myEntity);
        
        List<PhotoDTO> response = photoService.getPhotosByAlbumId(albumId);
        
        assertEquals(photos.get(0).getId(), response.get(0).getId());
    }
    
    @Test
    public void getPhotosByUserIdTest() {
        String userId = "1";
        
        List<AlbumDTO> albums = new ArrayList();
        AlbumDTO album = new AlbumDTO(1, 1, "album title");
        albums.add(album);
        when(albumService.getAlbumsByUserId(userId)).thenReturn(albums);
        
        List<PhotoDTO> photos = new ArrayList();
        PhotoDTO photo = new PhotoDTO(1, 1, "photo title", "photo url", "photo thumbnailUrl");
        photos.add(photo);
        String url = PHOTOS_URL + "?albumId=" + album.getId();
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        ParameterizedTypeReference<List<PhotoDTO>> typeRef = new ParameterizedTypeReference<List<PhotoDTO>>() {};
        ResponseEntity<List<PhotoDTO>> myEntity = new ResponseEntity<>(photos, HttpStatus.OK);
        when(restTemplate
                .exchange(url, HttpMethod.GET, entity, typeRef))
                .thenReturn(myEntity);
        
        List<PhotoDTO> response = photoService.getPhotosByUserId(userId);

        assertEquals(photos.get(0).getId(), response.get(0).getId());
    }
}
