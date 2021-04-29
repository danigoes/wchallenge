/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.AddressDTO;
import com.wolox.wchallenge.dto.CompanyDTO;
import com.wolox.wchallenge.dto.UserDTO;
import com.wolox.wchallenge.service.implementation.UserServiceImpl;
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

/**
 *
 * @author Daniela
 */
@RunWith(SpringRunner.class)
public class UserContollerTest {

    private static final UserDTO userGet = new UserDTO(1, "user name", "user username", "user email", new AddressDTO(), "user phone", "user website", new CompanyDTO());

    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImpl userService;

    @Before
    public void setUp() {
    }

    @Test
    public void getUsers_ReturnOK() {
        List<UserDTO> users = new ArrayList<>();
        UserDTO user = new UserDTO(
                userGet.getId(),
                userGet.getName(),
                userGet.getUsername(),
                userGet.getEmail(),
                userGet.getAddress(),
                userGet.getPhone(),
                userGet.getWebsite(),
                userGet.getCompany()
        );
        users.add(user);
        when(userService.getUsers()).thenReturn(users);
        ResponseEntity<List<UserDTO>> result = userController.getUsers();
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getUsers_ReturnNoContent() {
        List<UserDTO> albums = new ArrayList<>();
        when(userService.getUsers()).thenReturn(albums);
        ResponseEntity<List<UserDTO>> result = userController.getUsers();
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

}
