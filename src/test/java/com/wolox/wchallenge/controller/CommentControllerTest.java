/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.CommentDTO;
import com.wolox.wchallenge.service.implementation.CommentServiceImpl;
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
public class CommentControllerTest {

    private static final CommentDTO commentGet = new CommentDTO(1, 1, "quo", "email comment test", "body comment test");

    @InjectMocks
    private CommentController commentController;

    @Mock
    private CommentServiceImpl commentService;

    @Before
    public void setUp() {
    }

    @Test
    public void getComments_ReturnOK() {
        List<CommentDTO> comments = new ArrayList<>();
        CommentDTO comment = new CommentDTO(
                commentGet.getId(),
                commentGet.getPostId(),
                commentGet.getName(),
                commentGet.getEmail(),
                commentGet.getBody());
        comments.add(comment);
        when(commentService.getComments()).thenReturn(comments);
        ResponseEntity<List<CommentDTO>> result = commentController.getComments();
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getComments_ReturnNoContent() {
        List<CommentDTO> comments = new ArrayList<>();
        when(commentService.getComments()).thenReturn(comments);
        ResponseEntity<List<CommentDTO>> result = commentController.getComments();
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    public void getCommentFilterByName_ReturnOK() {
        CommentDTO commentBody = new CommentDTO();
        commentBody.setId(1);
        commentBody.setPostId(1);
        commentBody.setName("quo");
        commentBody.setEmail("email comment test");
        commentBody.setBody("body comment test");
        List<CommentDTO> comments = new ArrayList<>();
        comments.add(commentBody);
        when(commentService.getCommentFilterByName(commentBody.getName())).thenReturn(comments);
        ResponseEntity<List<CommentDTO>> result = commentController
                .getCommentFilterByName(commentBody);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getCommentFilterByName_ReturnNoContent() {
        CommentDTO commentBody = new CommentDTO();
        List<CommentDTO> comments = new ArrayList<>();
        when(commentService.getCommentFilterByName(commentBody.getName())).thenReturn(comments);
        ResponseEntity<List<CommentDTO>> result = commentController
                .getCommentFilterByName(commentBody);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    public void getCommentFilterByUserId_ReturnOK() {
        String userId = "1";
        CommentDTO commentBody = new CommentDTO(1, 1, "quo", "email comment test", "body comment test");
        List<CommentDTO> comments = new ArrayList<>();
        comments.add(commentBody);
        when(commentService.getCommentFilterByUserId(userId)).thenReturn(comments);
        ResponseEntity<List<CommentDTO>> result = commentController
                .getCommentFilterByUserId(userId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getCommentFilterByUserId_ReturnNoContent() {
        String userId = "1";
        List<CommentDTO> comments = new ArrayList<>();
        when(commentService.getCommentFilterByUserId(userId)).thenReturn(comments);
        ResponseEntity<List<CommentDTO>> result = commentController
                .getCommentFilterByUserId(userId);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
}
