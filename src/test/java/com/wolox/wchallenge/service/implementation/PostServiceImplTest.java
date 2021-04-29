/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.service.implementation;

import com.wolox.wchallenge.dto.PostDTO;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
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
public class PostServiceImplTest {
    
    public static final String POSTS_URL = "https://jsonplaceholder.typicode.com/posts";
    public static final PostDTO post = new PostDTO(1, 1, "post title", "post body");
    
    @Mock
    private RestTemplate restTemplate;
    
    @InjectMocks
    private PostServiceImpl postService;
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void getPostsByUserIdTest() {
        String userId = "1";
        List<PostDTO> posts = new ArrayList();
        PostDTO post1 = new PostDTO();
        post1.setId(post.getId());
        post1.setUserId(post.getUserId());
        post1.setTitle(post.getTitle());
        post1.setBody(post.getBody());
        PostDTO post2 = new PostDTO();
        post2.setId(1);
        post2.setUserId(1);
        post2.setTitle("post title 2");
        post2.setBody("post body 2");
        posts.add(post1);
        
        String url = POSTS_URL + "?userId=" + userId;
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        ParameterizedTypeReference<List<PostDTO>> typeRef = new ParameterizedTypeReference<List<PostDTO>>() {};
        ResponseEntity<List<PostDTO>> myEntity = new ResponseEntity<>(posts, HttpStatus.OK);
        when(restTemplate
                .exchange(url, HttpMethod.GET, entity, typeRef))
                .thenReturn(myEntity);
        
        List<PostDTO> response = postService.getPostsByUserId(userId);
        
        assertEquals(response.get(0).getUserId(), post.getUserId());
    }
}
