/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.service.implementation;

import com.wolox.wchallenge.dto.CommentDTO;
import com.wolox.wchallenge.dto.PostDTO;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Daniela
 */
@RunWith(SpringRunner.class)
public class CommentServiceImplTest {

    private static final String COMMENTS_URL = "http://jsonplaceholder.typicode.com/comments";

    @Mock
    private RestTemplate restTemplate;
    
    @Mock
    private PostServiceImpl postService;

    @InjectMocks
    private CommentServiceImpl commentService;

    public CommentServiceImplTest() {
    }

    @Test
    public void getCommentFilterByName_ReturnOK() {
        String name = "name";
        List<CommentDTO> comments = new ArrayList();
        CommentDTO comment = new CommentDTO();
        comment.setId(1);
        comment.setPostId(1);
        comment.setBody("comment body");
        comment.setEmail("comment email");
        comment.setName("comment name");
        comments.add(comment);

        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ParameterizedTypeReference<List<CommentDTO>> typeRef
                = new ParameterizedTypeReference<List<CommentDTO>>() {};
        ResponseEntity<List<CommentDTO>> myEntity = new ResponseEntity<>(comments, HttpStatus.OK);
        when(restTemplate
                .exchange(COMMENTS_URL, HttpMethod.GET, entity, typeRef))
                .thenReturn(myEntity);

        List<CommentDTO> response = commentService.getCommentFilterByName(name);

        assertEquals(response.get(0).getName(), comments.get(0).getName());
    }
    
    @Test
    public void getCommentsByPostId_ReturnOK() {
        String postId = "1";
        List<CommentDTO> comments = new ArrayList();
        CommentDTO comment = new CommentDTO();
        comment.setId(1);
        comment.setPostId(1);
        comment.setBody("comment body");
        comment.setEmail("comment email");
        comment.setName("comment name");
        comments.add(comment);

        String url = COMMENTS_URL + "?postId=" + postId;
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ParameterizedTypeReference<List<CommentDTO>> typeRef
                = new ParameterizedTypeReference<List<CommentDTO>>() {};
        ResponseEntity<List<CommentDTO>> myEntity = new ResponseEntity<>(comments, HttpStatus.OK);
        when(restTemplate
                .exchange(url, HttpMethod.GET, entity, typeRef))
                .thenReturn(myEntity);

        List<CommentDTO> response = commentService.getCommentsByPostId(postId);

        assertEquals(response.get(0).getName(), comments.get(0).getName());
    }
    
    @Test
    public void getCommentFilterByUserId_ReturnOK() {
        String userId = "1";
        List<PostDTO> posts = new ArrayList();
        PostDTO post = new PostDTO();
        post.setId(1);
        post.setBody("body post");
        post.setTitle("title post");
        post.setUserId(1);
        posts.add(post);
        List<CommentDTO> comments = new ArrayList();
        CommentDTO comment = new CommentDTO();
        comment.setId(1);
        comment.setPostId(1);
        comment.setBody("comment body");
        comment.setEmail("comment email");
        comment.setName("comment name");
        comments.add(comment);

        when(postService.getPostsByUserId(userId)).thenReturn(posts);

        String url = COMMENTS_URL + "?postId=" + post.getId().toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ParameterizedTypeReference<List<CommentDTO>> typeRef
                = new ParameterizedTypeReference<List<CommentDTO>>() {};
        ResponseEntity<List<CommentDTO>> myEntity = new ResponseEntity<>(comments, HttpStatus.OK);
        when(restTemplate
                .exchange(url, HttpMethod.GET, entity, typeRef))
                .thenReturn(myEntity);
                
        List<CommentDTO> response = commentService.getCommentFilterByUserId(userId);

        assertEquals(response.get(0).getPostId(), comments.get(0).getPostId());
    }
}
