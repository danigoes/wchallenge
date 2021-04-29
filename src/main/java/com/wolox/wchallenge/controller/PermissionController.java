/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.PermissionDTO;
import com.wolox.wchallenge.dto.UserDTO;
import com.wolox.wchallenge.exception.ApiRequestException;
import com.wolox.wchallenge.service.PermissionService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Daniela
 */
@Slf4j
@Api(value = "Permission Controller", description = "Manage information related with permissions")
@RestController
@RequestMapping("/wchallenge/api/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation(value = "Create a new permission for shared album")
    @PostMapping()
    public ResponseEntity<PermissionDTO> createPermissionSharedAlbum(
            @ApiParam( value = "Object of type Permission", required = true)
            @RequestBody PermissionDTO permission) {
        log.info("Create permission for a shared album...");
        PermissionDTO response = permissionService.createPermissionSharedAlbum(permission);
        if (response == null) {
            log.info("Cannot create permission of shared album");
            throw new ApiRequestException("Cannot create permission of shared album");
        } else {
            log.info("Permission created successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    @ApiOperation(value = "Update a permission for shared album")
    @PutMapping()
    public ResponseEntity<PermissionDTO> updateTypePermissionSharedAlbum(
            @ApiParam( value = "Object of type Permission", required = true)
            @RequestBody PermissionDTO permission) {
        log.info("Update type of permission for shared album...");
        PermissionDTO response = permissionService.updateTypePermissionSharedAlbum(permission);
        if (response == null) {
            log.info("Cannot update permission of shared album");
            throw new ApiRequestException("Cannot update permission of shared album");
        } else {
            log.info("Permission updated successfully");
            return ResponseEntity.ok(response);
        }
    }

    @ApiOperation(value = "Get list of users who has any type of permission in a specific album")
    @GetMapping("/users/type/{typePermissionId}/album/{albumId}")
    public ResponseEntity<List<UserDTO>> getUsersByTypePermissionIdAndAlbumId(
            @ApiParam( value = "Type permission identification", required = true)
            @PathVariable("typePermissionId") Integer typePermissionId,
            @ApiParam( value = "Album identification", required = true)
            @PathVariable("albumId") Integer albumId) {
        log.info("Get list of uses who has any type of permission in a specific album...");
        List<UserDTO> response = permissionService
                .getUsersByTypePermissionIdAndAlbumId(typePermissionId, albumId);
        if (response == null) {
            log.info("No content");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            log.info("Users by type permission id {} and album id {} obtained successfully", 
                    typePermissionId, albumId);
            return ResponseEntity.ok(response);
        }
    }
}
