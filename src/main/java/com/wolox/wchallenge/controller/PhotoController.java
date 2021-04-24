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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
        return ResponseEntity.ok(response);
    }
}
