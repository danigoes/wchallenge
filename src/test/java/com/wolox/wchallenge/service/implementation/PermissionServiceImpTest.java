/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.service.implementation;

import com.wolox.wchallenge.dto.AddressDTO;
import com.wolox.wchallenge.dto.AlbumDTO;
import com.wolox.wchallenge.dto.CompanyDTO;
import com.wolox.wchallenge.dto.PermissionDTO;
import com.wolox.wchallenge.dto.UserDTO;
import com.wolox.wchallenge.exception.ApiRequestException;
import com.wolox.wchallenge.exception.DataNotFoundException;
import com.wolox.wchallenge.model.Permission;
import com.wolox.wchallenge.model.TypePermission;
import com.wolox.wchallenge.repository.PermissionRepository;
import com.wolox.wchallenge.repository.TypePermissionRepository;
import com.wolox.wchallenge.service.AlbumService;
import com.wolox.wchallenge.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Daniela
 */
@RunWith(SpringRunner.class)
public class PermissionServiceImpTest {
    
    @Mock
    private AlbumService albumService;

    @Mock
    private UserService userService;

    @Mock
    private PermissionRepository permissionRepository;

    @Mock
    private TypePermissionRepository typePermissionRepository;

    @Mock
    private ModelMapper modelMapper;
    
    @InjectMocks
    private PermissionServiceImpl permissionService;
    
    @Test
    public void createPermissionSharedAlbumTest() {
        PermissionDTO permissionCreate = new PermissionDTO(1, 1, 1, 1, "READ");
        Permission permission = new Permission(1, 1, 1, 1);
        when(modelMapper.map(permissionCreate, Permission.class)).thenReturn(permission);
        
        when(permissionRepository.save(permission)).thenReturn(permission);
        
        when(modelMapper.map(permission, PermissionDTO.class)).thenReturn(permissionCreate);
        
        PermissionDTO response = permissionService.createPermissionSharedAlbum(permissionCreate);
        
        assertEquals(response.getId(), permissionCreate.getId());
    }
    
    @Test
    public void updateTypePermissionSharedAlbumTest() {
        PermissionDTO permission = new PermissionDTO(1, 1, 1, 1, "READ");
        Permission permissionModelNew = new Permission(1, 1, 1, 2);
        Permission permissionNew = new Permission();
        permissionNew.setId(permissionModelNew.getId());
        permissionNew.setAlbumId(permissionModelNew.getAlbumId());
        permissionNew.setUserId(permissionModelNew.getUserId());
        permissionNew.setTypePermissionId(permissionModelNew.getTypePermissionId());
        Optional<Permission> permissionOptional = Optional.of(permissionNew);
        Permission newPermission = permissionOptional.get();
        TypePermission typePermission = new TypePermission();
        typePermission.setId(1);
        typePermission.setTypePermission("WRITE");
        TypePermission typePermissionTest = new TypePermission(2, "WRITE");
        Optional<TypePermission> typePermissionOptional = Optional.of(typePermission);
        PermissionDTO permissionResponse = new PermissionDTO(1, 1, 1, typePermission.getId(), typePermissionTest.getTypePermission());
        
        when(permissionRepository.findById(permission.getId())).thenReturn(permissionOptional);
        
        when(permissionRepository.save(newPermission)).thenReturn(newPermission);
        
        when(typePermissionRepository.findById(permission.getTypePermissionId())).thenReturn(typePermissionOptional);
        
        when(modelMapper.map(newPermission, PermissionDTO.class)).thenReturn(permissionResponse);
        
        PermissionDTO response = permissionService.updateTypePermissionSharedAlbum(permission);
        
        assertEquals(response.getId(), permissionResponse.getId());
    }
    
    @Test(expected = DataNotFoundException.class)
    public void updateTypePermissionSharedAlbumTest_NotFound() throws DataNotFoundException {
        PermissionDTO permission = new PermissionDTO(null, 1, 1, 1, "READ");
     
        PermissionDTO response = permissionService.updateTypePermissionSharedAlbum(permission);
        
        assertEquals(response, "Not found permission to update");
    }
    
    @Test
    public void getUsersByTypePermissionIdAndAlbumId() {
        Integer typePermissionId = 1;
        Integer albumId = 1;
        AlbumDTO album = new AlbumDTO(1, 1, "album title");
        when(albumService.getAlbumById(albumId.toString())).thenReturn(album);
        UserDTO user = new UserDTO(1, "user name", "user username", "user email", new AddressDTO(), "user phone", "user website", new CompanyDTO());
        when(userService.getUserById(album.getUserId().toString())).thenReturn(user);
        Permission permission = new Permission(1, 1, 1, 1);
        List<Permission> permissions = new ArrayList();
        permissions.add(permission);
        when(permissionRepository.findByAlbumIdAndTypePermissionId(albumId, typePermissionId)).thenReturn(permissions);
        when(userService.getUserById(album.getUserId().toString())).thenReturn(user);

        List<UserDTO> response = permissionService.getUsersByTypePermissionIdAndAlbumId(typePermissionId, albumId);
        
        assertEquals(response.get(0).getId(), user.getId());
    }
    
    @Test(expected = DataNotFoundException.class)
    public void getUsersByTypePermissionIdAndAlbumId_AlbumIdNull() {
        Integer typePermissionId = 1;
        Integer albumId = 1;
        AlbumDTO album = new AlbumDTO();
        when(albumService.getAlbumById(albumId.toString())).thenReturn(album);
        
        List<UserDTO> response = permissionService.getUsersByTypePermissionIdAndAlbumId(typePermissionId, albumId);
        
        assertEquals(response, "Album data by album id not found");
    }
    
    @Test(expected = DataNotFoundException.class)
    public void getUsersByTypePermissionIdAndAlbumId_UserIdNull() {
        Integer typePermissionId = 1;
        Integer albumId = 1;
        AlbumDTO album = new AlbumDTO(1, 1, "album title");
        when(albumService.getAlbumById(albumId.toString())).thenReturn(album);
        UserDTO user = new UserDTO();
        when(userService.getUserById(album.getUserId().toString())).thenReturn(user);
        List<UserDTO> response = permissionService.getUsersByTypePermissionIdAndAlbumId(typePermissionId, albumId);
        
        assertEquals(response, "User data not found");
    }
}
