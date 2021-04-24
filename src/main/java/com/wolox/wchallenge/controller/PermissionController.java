/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.PermissionDTO;
import com.wolox.wchallenge.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Daniela
 */
@RestController
@RequestMapping("/wchallenge/api/permissions")
public class PermissionController {
    
    @Autowired
    private PermissionService permissionService;
    
    @PostMapping("/createPermissionSharedAlbum")
    public ResponseEntity<PermissionDTO> createPermissionSharedAlbum(@RequestBody PermissionDTO permission) {
        PermissionDTO response = permissionService.createPermissionSharedAlbum(permission);
        return ResponseEntity.ok(response);
    }
}
