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
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Daniela
 */
@Service
public class CommentServiceImpl implements CommentService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private PostService postService;

    private static final String COMMENTS_URL = "http://jsonplaceholder.typicode.com/comments";

    @Override
    public List<CommentDTO> getCommentFilterByName(String name) {
        List<CommentDTO> response = new ArrayList();
        List<CommentDTO> comments = this.getComments();
        comments.stream().filter((comment) -> (comment.getName().equals(name))).forEachOrdered((comment) -> {
            response.add(comment);
        });
        return response;
    }

    @Override
    public List<CommentDTO> getComments() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        List<CommentDTO> response = Arrays.asList(restTemplate.exchange(COMMENTS_URL, HttpMethod.GET, entity, CommentDTO[].class).getBody());
        return response;
    }
    
    public List<CommentDTO> getCommentsByPostId(String postId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        List<CommentDTO> response = Arrays.asList(restTemplate.exchange(COMMENTS_URL + "?postId=" + postId, HttpMethod.GET, entity, CommentDTO[].class).getBody());
        return response;
    }

    @Override
    public List<CommentDTO> getCommentFilterByUserId(String userId) {
        List<CommentDTO> response = new ArrayList();
        List<PostDTO> posts = postService.getPostsByUserId(userId);
        posts.stream().map((post) -> this.getCommentsByPostId(post.getId().toString())).forEachOrdered((comments) -> {
            response.addAll(comments);
        });
        return response;
    }

}
