/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.AlbumDTO;
import com.wolox.wchallenge.service.AlbumService;
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
@Api(value = "Album Controller", description = "Get information related with albums")
@RestController
@RequestMapping("/wchallenge/api/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @ApiOperation(value = "Get list of albums")
    @GetMapping()
    public ResponseEntity<List<AlbumDTO>> getAlbums() {
        log.info("Get list of albums...");
        List<AlbumDTO> response = albumService.getAlbums();
        if (response.isEmpty()) {
            log.info("No content");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            log.info("Albums successfully obtained");
            return ResponseEntity.ok(response);
        }
    }

    @ApiOperation(value = "Get album for a specific user")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AlbumDTO>> getAlbumsByUserId(
            @ApiParam( value = "User identification", required = true) 
            @PathVariable("userId") String userId) {
        log.info("Get list of albums by user id...");
        List<AlbumDTO> response = albumService.getAlbumsByUserId(userId);
        if (response.isEmpty()) {
            log.info("No content");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            log.info("Albums by user id {} successfully obtained", userId);
            return ResponseEntity.ok(response);
        }
    }

}
