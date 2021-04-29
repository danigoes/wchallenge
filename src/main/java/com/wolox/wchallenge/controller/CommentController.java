/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.CommentDTO;
import com.wolox.wchallenge.service.CommentService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Daniela
 */
@Slf4j
@Api(value = "Comment Controller", description = "Get information related with comments")
@RestController
@RequestMapping("/wchallenge/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "Get list of comments")
    @GetMapping()
    public ResponseEntity<List<CommentDTO>> getComments() {
        log.info("Get list of comments...");
        List<CommentDTO> response = commentService.getComments();
        if (response.isEmpty()) {
            log.info("No content");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            log.info("Comments obtained successfully");
            return ResponseEntity.ok(response);
        }
    }

    @ApiOperation(value = "Get list of comments filtered by name")
    @GetMapping("/name")
    public ResponseEntity<List<CommentDTO>> getCommentFilterByName(
            @ApiParam( value = "Object of type Comment", required = true) 
            @RequestBody CommentDTO comment) {
        log.info("Get list of comments filtered by name...");
        List<CommentDTO> response = commentService.getCommentFilterByName(comment.getName());
        if (response.isEmpty()) {
            log.info("No content");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            log.info("Comments obtained successfully");
            return ResponseEntity.ok(response);
        }
    }

    @ApiOperation(value = "Get list of comments filtered by user identification")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentDTO>> getCommentFilterByUserId(
            @ApiParam( value = "User identification", required = true) 
            @PathVariable("userId") String userId) {
        log.info("Get list of comments filtered by user id...");
        List<CommentDTO> response = commentService.getCommentFilterByUserId(userId);
        if (response.isEmpty()) {
            log.info("No content");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            log.info("Comments obtained successfully");
            return ResponseEntity.ok(response);
        }
    }

}
