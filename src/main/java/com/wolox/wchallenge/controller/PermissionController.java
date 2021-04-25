/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.PermissionDTO;
import com.wolox.wchallenge.dto.UserDTO;
import com.wolox.wchallenge.service.PermissionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PutMapping("/updateTypePermissionSharedAlbum")
    public ResponseEntity<PermissionDTO> updateTypePermissionSharedAlbum(@RequestBody PermissionDTO permission) {
        PermissionDTO response = permissionService.updateTypePermissionSharedAlbum(permission);
        if (response == null) {
            return null;
        } else {
            return ResponseEntity.ok(response);
        }
    }
    
    @GetMapping("/users/ByTypePermissionId/{typePermissionId}/ByAlbumId/{albumId}")
    public ResponseEntity<List<UserDTO>> updateTypePermissionSharedAlbum(
            @PathVariable("typePermissionId") Integer typePermissionId,
            @PathVariable("albumId") Integer albumId) {
        List<UserDTO> response = permissionService.getUsersByTypePermissionIdAndAlbumId(typePermissionId, albumId);
        if (response == null) {
            return null;
        } else {
            return ResponseEntity.ok(response);
        }
    }
}
