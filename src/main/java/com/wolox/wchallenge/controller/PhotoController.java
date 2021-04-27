/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.PhotoDTO;
import com.wolox.wchallenge.service.PhotoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Daniela
 */
@RestController
@RequestMapping("/wchallenge/api/photos")
public class PhotoController {
    
    @Autowired
    private PhotoService photoService;
    
    @GetMapping()
    public ResponseEntity<List<PhotoDTO>> getPhotos() {
        List<PhotoDTO> response = this.photoService.getPhotos();
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(response);
        }
    }
    
    @GetMapping("/album/{albumId}")
    public ResponseEntity<List<PhotoDTO>> getPhotosByAlbumId(
            @PathVariable("albumId") String albumId) {
        List<PhotoDTO> response = this.photoService.getPhotos();
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(response);
        }
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PhotoDTO>> getPhotosByUserId(
            @PathVariable("userId") String userId) {
        List<PhotoDTO> response = this.photoService.getPhotosByUserId(userId);
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(response);
        }
    }
}
