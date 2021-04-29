/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.service.implementation;

import com.wolox.wchallenge.dto.CommentDTO;
import com.wolox.wchallenge.dto.PostDTO;
import com.wolox.wchallenge.service.CommentService;
import com.wolox.wchallenge.service.PostService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Daniela
 */
@Service
public class CommentServiceImpl implements CommentService {

    private static final String COMMENTS_URL = "http://jsonplaceholder.typicode.com/comments";
    private static final ParameterizedTypeReference<List<CommentDTO>> typeRef = 
                new ParameterizedTypeReference<List<CommentDTO>>() {};

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PostService postService;

    @Override
    public List<CommentDTO> getCommentFilterByName(String name) {
        List<CommentDTO> response = new ArrayList();
        List<CommentDTO> comments = this.getComments();
        comments.stream().filter((comment) -> (comment.getName().contains(name)))
                .forEachOrdered((comment) -> {
                    response.add(comment);
                });
        return response;
    }

    @Override
    public List<CommentDTO> getComments() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<CommentDTO>> response = restTemplate
                .exchange(COMMENTS_URL, HttpMethod.GET, entity, typeRef);
        return response.getBody();
    }

    public List<CommentDTO> getCommentsByPostId(String postId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = COMMENTS_URL + "?postId=" + postId;
        ResponseEntity<List<CommentDTO>> response = restTemplate
                .exchange(url, HttpMethod.GET, entity, typeRef);
        return response.getBody();
    }

    @Override
    public List<CommentDTO> getCommentFilterByUserId(String userId) {
        List<CommentDTO> response = new ArrayList();
        List<PostDTO> posts = postService.getPostsByUserId(userId);
        posts.stream().map((post) -> getCommentsByPostId(post.getId().toString()))
                .forEachOrdered((comments) -> {
                    response.addAll(comments);
                });
        return response;
    }

}
