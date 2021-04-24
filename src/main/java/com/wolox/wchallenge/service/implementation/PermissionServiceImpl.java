/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.service.implementation;

import com.wolox.wchallenge.dto.PermissionDTO;
import com.wolox.wchallenge.model.Permission;
import com.wolox.wchallenge.model.TypePermission;
import com.wolox.wchallenge.repository.PermissionRepository;
import com.wolox.wchallenge.repository.TypePermissionRepository;
import com.wolox.wchallenge.service.PermissionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniela
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;
    
    @Autowired
    private TypePermissionRepository typePermissionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PermissionDTO createPermissionSharedAlbum(PermissionDTO permission) {
        Permission permissionAux = modelMapper.map(permission, Permission.class);
        permissionAux = this.permissionRepository.save(permissionAux);
        return modelMapper.map(permissionAux, PermissionDTO.class);
    }

    @Override
    public PermissionDTO updateTypePermissionSharedAlbum(PermissionDTO permission) {
        if (permission.getId() != null) {
            Permission oldPermission = this.permissionRepository.findById(permission.getId()).get();
            Permission newPermission = modelMapper.map(oldPermission, Permission.class);
            newPermission.setTypePermissionId(permission.getTypePermissionId());
            newPermission = this.permissionRepository.save(newPermission);
            TypePermission typePermission = typePermissionRepository.findById(permission.getTypePermissionId()).get();
            PermissionDTO response = modelMapper.map(newPermission, PermissionDTO.class);
            response.setTypePermission(typePermission.getTypePermission());
            return response;
        } else {
            return null;
        }
    }
}
