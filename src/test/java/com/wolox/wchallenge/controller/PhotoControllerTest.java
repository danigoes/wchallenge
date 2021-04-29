package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.PhotoDTO;
import com.wolox.wchallenge.service.implementation.PhotoServiceImpl;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Daniela
 */
@RunWith(SpringRunner.class)
public class PhotoControllerTest {

    @InjectMocks
    private PhotoController photoController;

    @Mock
    private PhotoServiceImpl photoService;

    @Before
    public void setUp() {
    }

    @Test
    public void getPhotos_ReturnOK() {
        PhotoDTO photoGet = new PhotoDTO(1, 1, "photo title", "photo url", "photo thumbnailUrl");
        List<PhotoDTO> photos = new ArrayList<>();
        PhotoDTO photo = new PhotoDTO(
                photoGet.getId(),
                photoGet.getAlbumId(),
                photoGet.getTitle(),
                photoGet.getUrl(),
                photoGet.getThumbnailUrl());
        photos.add(photo);
        when(photoService.getPhotos()).thenReturn(photos);
        ResponseEntity<List<PhotoDTO>> result = photoController.getPhotos();
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    
    @Test
    public void getPhotos_ReturnNoContent() {
        List<PhotoDTO> photos = new ArrayList<>();
        when(photoService.getPhotos()).thenReturn(photos);
        ResponseEntity<List<PhotoDTO>> result = photoController.getPhotos();
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
    
    @Test
    public void getPhotosByAlbumId_ReturnOK() {
        String albumId = "1";
        List<PhotoDTO> photos = new ArrayList<>();
        PhotoDTO photo = new PhotoDTO();
        photo.setId(1);
        photo.setAlbumId(1);
        photo.setTitle("title");
        photo.setThumbnailUrl("thumbnail");
        photo.setUrl("url");
        photos.add(photo);
        when(photoService.getPhotosByAlbumId(albumId)).thenReturn(photos);
        ResponseEntity<List<PhotoDTO>> result = photoController.getPhotosByAlbumId(albumId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    
    @Test
    public void getPhotosByAlbumId_ReturnNoContent() {
        String albumId = "1";
        List<PhotoDTO> photos = new ArrayList<>();
        when(photoService.getPhotosByAlbumId(albumId)).thenReturn(photos);
        ResponseEntity<List<PhotoDTO>> result = photoController.getPhotosByAlbumId(albumId);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
    
    @Test
    public void getPhotosByUserId_ReturnOK() {
        String userId = "1";
        List<PhotoDTO> photos = new ArrayList<>();
        PhotoDTO photo = new PhotoDTO();
        photo.setId(1);
        photo.setAlbumId(1);
        photo.setTitle("title");
        photo.setThumbnailUrl("thumbnail");
        photo.setUrl("url");
        photos.add(photo);
        when(photoService.getPhotosByUserId(userId)).thenReturn(photos);
        ResponseEntity<List<PhotoDTO>> result = photoController.getPhotosByUserId(userId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    
    @Test
    public void getPhotosByUserId_ReturnNoContent() {
        String userId = "1";
        List<PhotoDTO> photos = new ArrayList<>();
        when(photoService.getPhotosByUserId(userId)).thenReturn(photos);
        ResponseEntity<List<PhotoDTO>> result = photoController.getPhotosByUserId(userId);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
    
}
