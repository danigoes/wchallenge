/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.CommentDTO;
import com.wolox.wchallenge.service.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/wchallenge/api/comments")
public class CommentController {
    
    @Autowired
    private CommentService commentService;
    
    @GetMapping()
    public ResponseEntity<List<CommentDTO>> getComments() {
        List<CommentDTO> response = this.commentService.getComments();
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/filterByName")
    public ResponseEntity<List<CommentDTO>> getCommentFilterByName(@RequestBody CommentDTO comment) {
        List<CommentDTO> response = this.commentService.getCommentFilterByName(comment.getName());
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/filterByUserId/{userId}")
    public ResponseEntity<List<CommentDTO>> getCommentFilterByUserId(@PathVariable("userId") String userId) {
        List<CommentDTO> response = this.commentService.getCommentFilterByUserId(userId);
        return ResponseEntity.ok(response);
    }

}
