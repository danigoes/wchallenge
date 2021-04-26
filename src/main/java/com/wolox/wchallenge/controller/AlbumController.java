/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.AlbumDTO;
import com.wolox.wchallenge.service.AlbumService;
import java.util.List;
import javassist.NotFoundException;
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
@RequestMapping("/wchallenge/api/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping()
    public ResponseEntity<List<AlbumDTO>> getAlbums() throws NotFoundException {
        List<AlbumDTO> response = albumService.getAlbums();
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/byUserId/{userId}")
    public ResponseEntity<List<AlbumDTO>> getAlbumsByUserId(@PathVariable("userId") String userId) {
        List<AlbumDTO> response = albumService.getAlbumsByUserId(userId);
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(response);
        }
    }

}
