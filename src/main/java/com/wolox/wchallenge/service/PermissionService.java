/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.service;

import com.wolox.wchallenge.dto.PermissionDTO;

/**
 *
 * @author Daniela
 */
public interface PermissionService {
    
    PermissionDTO createPermissionSharedAlbum(PermissionDTO permission);
    PermissionDTO updateTypePermissionSharedAlbum(PermissionDTO permission);
}
