/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.service.implementation;

import com.wolox.wchallenge.dto.AlbumDTO;
import com.wolox.wchallenge.dto.PermissionDTO;
import com.wolox.wchallenge.dto.UserDTO;
import com.wolox.wchallenge.exception.DataNotFoundException;
import com.wolox.wchallenge.model.Permission;
import com.wolox.wchallenge.model.TypePermission;
import com.wolox.wchallenge.repository.PermissionRepository;
import com.wolox.wchallenge.repository.TypePermissionRepository;
import com.wolox.wchallenge.service.AlbumService;
import com.wolox.wchallenge.service.PermissionService;
import com.wolox.wchallenge.service.UserService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniela
 */
@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private TypePermissionRepository typePermissionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PermissionDTO createPermissionSharedAlbum(PermissionDTO permission) {
        log.info("Creating the permission");
        Permission permissionAux = modelMapper.map(permission, Permission.class);
        permissionAux = permissionRepository.save(permissionAux);
        return modelMapper.map(permissionAux, PermissionDTO.class);
    }

    @Override
    public PermissionDTO updateTypePermissionSharedAlbum(PermissionDTO permission) {
        log.info("Updating the permission");
        if (permission.getId() != null) {
            Permission newPermission = this.permissionRepository
                    .findById(permission.getId()).get();
            newPermission.setTypePermissionId(permission.getTypePermissionId());
            newPermission = permissionRepository.save(newPermission);
            TypePermission typePermission = typePermissionRepository
                    .findById(permission.getTypePermissionId()).get();
            PermissionDTO response = modelMapper.map(newPermission, PermissionDTO.class);
            response.setTypePermission(typePermission.getTypePermission());
            return response;
        } else {
            log.error("Not found permission to update");
            throw new DataNotFoundException("Not found permission to update");
        }
    }

    @Override
    public List<UserDTO> getUsersByTypePermissionIdAndAlbumId(
            Integer typePermissionId, Integer albumId) {
        log.info("Getting all users");
        AlbumDTO album = albumService.getAlbumById(albumId.toString());
        if (album.getId() == null) {
            log.error("Album data by album id not found");
            throw new DataNotFoundException("Album data by album id not found");
        }
        Integer userIdOwner = album.getUserId();
        List<UserDTO> response = new ArrayList();

        UserDTO userOwner = userService.getUserById(userIdOwner.toString());
        if (userOwner.getId() == null) {
            log.error("User data not found");
            throw new DataNotFoundException("User data not found");
        }
        response.add(userOwner);

        List<Permission> permissions = permissionRepository
                .findByAlbumIdAndTypePermissionId(albumId, typePermissionId);
        permissions.stream().map((permission) -> userService
                .getUserById(permission.getUserId().toString()))
                .forEachOrdered((userExtra) -> {
                    response.add(userExtra);
                });
        return response;
    }
}
