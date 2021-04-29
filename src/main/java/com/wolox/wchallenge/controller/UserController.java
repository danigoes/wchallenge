/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.UserDTO;
import com.wolox.wchallenge.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Api(value = "User Controller", description = "Get information related with users")
@RestController
@RequestMapping("/wchallenge/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @ApiOperation(value = "Get list of users")
    @GetMapping()
    public ResponseEntity<List<UserDTO>> getUsers() {
        log.info("Get list of users...");
        List<UserDTO> response = userService.getUsers();
        if (response.isEmpty()) {
            log.info("No content");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            log.info("Users obtained successfully");
            return ResponseEntity.ok(response);
        }
    }
}
