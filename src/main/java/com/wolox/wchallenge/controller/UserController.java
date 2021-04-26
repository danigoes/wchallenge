/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.UserDTO;
import com.wolox.wchallenge.exception.DataNotFoundException;
import com.wolox.wchallenge.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Daniela
 */
@RestController
@RequestMapping("/wchallenge/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping()
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> response = this.userService.getUsers();
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(response);
        }
    }
}
