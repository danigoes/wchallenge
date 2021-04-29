/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.PhotoDTO;
import com.wolox.wchallenge.service.PhotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Api(value = "Photo Controller", description = "Get information related with photos")
@RestController
@RequestMapping("/wchallenge/api/photos")
public class PhotoController {
    
    @Autowired
    private PhotoService photoService;
    
    @ApiOperation(value = "Get list of photos")
    @GetMapping()
    public ResponseEntity<List<PhotoDTO>> getPhotos() {
        log.info("Get list of photos...");
        List<PhotoDTO> response = photoService.getPhotos();
        if (response.isEmpty()) {
            log.info("No content");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            log.info("Photos obtained successfully");
            return ResponseEntity.ok(response);
        }
    }
    
    @ApiOperation(value = "Get list of photos by album identification")
    @GetMapping("/album/{albumId}")
    public ResponseEntity<List<PhotoDTO>> getPhotosByAlbumId(
            @ApiParam( value = "Album identification", required = true) 
            @PathVariable("albumId") String albumId) {
        log.info("Get list od photos by album id...");
        List<PhotoDTO> response = this.photoService.getPhotosByAlbumId(albumId);
        if (response.isEmpty()) {
            log.info("No content");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            log.info("Photos obtained successfully");
            return ResponseEntity.ok(response);
        }
    }
    
    @ApiOperation(value = "Get list of photos by user identification")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PhotoDTO>> getPhotosByUserId(
            @ApiParam( value = "User identification", required = true) 
            @PathVariable("userId") String userId) {
        log.info("Get list of photos by user id...");
        List<PhotoDTO> response = this.photoService.getPhotosByUserId(userId);
        if (response.isEmpty()) {
            log.info("No content");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            log.info("Photos obtained successfully");
            return ResponseEntity.ok(response);
        }
    }
}
