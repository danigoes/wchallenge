package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.AddressDTO;
import com.wolox.wchallenge.dto.CompanyDTO;
import com.wolox.wchallenge.dto.PermissionDTO;
import com.wolox.wchallenge.dto.UserDTO;
import com.wolox.wchallenge.exception.ApiException;
import com.wolox.wchallenge.exception.ApiExceptionHandler;
import com.wolox.wchallenge.exception.ApiRequestException;
import com.wolox.wchallenge.exception.DataNotFoundException;
import com.wolox.wchallenge.service.implementation.PermissionServiceImpl;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Daniela
 */
@RunWith(SpringRunner.class)
public class PermissionControllerTest {

    @InjectMocks
    private PermissionController permissionController;

    @Mock
    private PermissionServiceImpl permissionService;

    @Mock
    private ApiExceptionHandler exceptionHandler;
    
    @Before
    public void setUp() {
    }

    @Test
    public void createPermissionSharedAlbum_ReturnCreated() {
        PermissionDTO permissionBody = new PermissionDTO();
        permissionBody.setId(1);
        permissionBody.setAlbumId(1);
        permissionBody.setUserId(1);
        permissionBody.setTypePermissionId(1);
        permissionBody.setTypePermission("READ");
        when(permissionService.createPermissionSharedAlbum(permissionBody))
                .thenReturn(permissionBody);
        ResponseEntity<PermissionDTO> result = permissionController
                .createPermissionSharedAlbum(permissionBody);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }
    
    @Test(expected = ApiRequestException.class)
    public void createPermissionSharedAlbum_ReturnBadRequest() {
        PermissionDTO permissionBody = new PermissionDTO();
        when(permissionService.createPermissionSharedAlbum(permissionBody))
                .thenReturn(null);
        ResponseEntity<PermissionDTO> result = permissionController
                .createPermissionSharedAlbum(permissionBody);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
    
    @Test
    public void updateTypePermissionSharedAlbum_ReturnOK() {
        PermissionDTO permissionGet = new PermissionDTO(1, 1, 1, 1, "READ");
        PermissionDTO permissionBody = new PermissionDTO();
        permissionBody.setId(permissionGet.getId());
        permissionBody.setUserId(permissionGet.getUserId());
        permissionBody.setAlbumId(permissionGet.getAlbumId());
        permissionBody.setTypePermissionId(2);
        permissionBody.setTypePermission("WRITE");
        when(permissionService.updateTypePermissionSharedAlbum(permissionBody))
                .thenReturn(permissionBody);
        ResponseEntity<PermissionDTO> result = permissionController
                .updateTypePermissionSharedAlbum(permissionBody);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(result.getBody().getTypePermission(), permissionBody.getTypePermission());
        assertEquals(result.getBody().getTypePermissionId(), permissionBody.getTypePermissionId());
    }
    
    @Test(expected = ApiRequestException.class)
    public void updateTypePermissionSharedAlbum_ReturnBadRequest() {
        PermissionDTO permissionBody = new PermissionDTO();
        ApiException exception = new ApiException("Cannot update permission of shared album", HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
        ApiExceptionHandler exceptionHandler = new ApiExceptionHandler();
        exceptionHandler.handleAllExceptions(new Exception());
        when(permissionService.updateTypePermissionSharedAlbum(permissionBody))
                .thenReturn(null);
        ResponseEntity<PermissionDTO> result = permissionController
                .updateTypePermissionSharedAlbum(permissionBody);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
    
    @Test
    public void getUsersByTypePermissionIdAndAlbumId_ReturnOK() {
        Integer typePermissionId = 1;
        Integer albumId = 1;
        List<UserDTO> users = new ArrayList();
        AddressDTO address = new AddressDTO();
        CompanyDTO company = new CompanyDTO();
        UserDTO user = new UserDTO(1, "name user", "username user", "email user", address, "phone user", "website user", company);
        when(permissionService.getUsersByTypePermissionIdAndAlbumId(typePermissionId, albumId))
                .thenReturn(users);
        ResponseEntity<List<UserDTO>> result = permissionController
                .getUsersByTypePermissionIdAndAlbumId(typePermissionId, albumId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    
    @Test
    public void getUsersByTypePermissionIdAndAlbumId_ReturnNoContent() {
        Integer typePermissionId = 1;
        Integer albumId = 1;
        when(permissionService.getUsersByTypePermissionIdAndAlbumId(typePermissionId, albumId))
                .thenReturn(null);
        ResponseEntity<List<UserDTO>> result = permissionController
                .getUsersByTypePermissionIdAndAlbumId(typePermissionId, albumId);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
       
    
}
