/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.AlbumDTO;
import com.wolox.wchallenge.service.implementation.AlbumServiceImpl;
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

/**
 *
 * @author Daniela
 */
@RunWith(SpringRunner.class)
public class AlbumControllerTest {

    private static final AlbumDTO albumGet = new AlbumDTO(1, 1, "album test");

    @InjectMocks
    private AlbumController albumController;

    @Mock
    private AlbumServiceImpl albumService;

    @Before
    public void setUp() {
    }

    @Test
    public void getAlbums_ReturnOK() {
        List<AlbumDTO> albums = new ArrayList<>();
        AlbumDTO album = new AlbumDTO(
                albumGet.getId(),
                albumGet.getUserId(),
                albumGet.getTitle());
        albums.add(album);
        when(albumService.getAlbums()).thenReturn(albums);
        ResponseEntity<List<AlbumDTO>> result = albumController.getAlbums();
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getAlbums_ReturnNoContent() {
        List<AlbumDTO> albums = new ArrayList<>();
        when(albumService.getAlbums()).thenReturn(albums);
        ResponseEntity<List<AlbumDTO>> result = albumController.getAlbums();
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    public void getAlbumsByUserId_ReturnOK() {
        String userId = "1";
        List<AlbumDTO> albums = new ArrayList<>();
        AlbumDTO album = new AlbumDTO();
        album.setId(1);
        album.setUserId(1);
        album.setTitle("album test");
        albums.add(album);
        when(albumService.getAlbumsByUserId(userId)).thenReturn(albums);
        ResponseEntity<List<AlbumDTO>> result = albumController.getAlbumsByUserId(userId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getAlbumsByUserId_ReturnNoContent() {
        String userId = "1";
        List<AlbumDTO> albums = new ArrayList<>();
        when(albumService.getAlbumsByUserId(userId)).thenReturn(albums);
        ResponseEntity<List<AlbumDTO>> result = albumController.getAlbumsByUserId(userId);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
}
