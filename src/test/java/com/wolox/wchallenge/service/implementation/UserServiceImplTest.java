/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.service.implementation;

import com.wolox.wchallenge.dto.AddressDTO;
import com.wolox.wchallenge.dto.CompanyDTO;
import com.wolox.wchallenge.dto.GeoDTO;
import com.wolox.wchallenge.dto.UserDTO;
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
public class UserServiceImplTest {
    
    public static final String USERS_URL = "https://jsonplaceholder.typicode.com/users";
    public static final GeoDTO geo = new GeoDTO("lat", "lng");
    public static final AddressDTO address = new AddressDTO("street", "suite", "city", "zipcode", geo);
    public static final CompanyDTO company = new CompanyDTO("name", "catchPhrase", "bs");
    
    @Mock
    private RestTemplate restTemplate;
    
    @InjectMocks
    private UserServiceImpl userService;
    
    @Test
    public void getUsersTest() {
        List<UserDTO> users = new ArrayList();
        GeoDTO geo1 = new GeoDTO();
        geo1.setLat(geo.getLat());
        geo1.setLng(geo.getLng());
        AddressDTO address1 = new AddressDTO();
        address1.setStreet("street");
        address1.setSuite("suite");
        address1.setCity("city");
        address1.setZipcode("zipcode");
        address1.setGeo(geo1);
        UserDTO user = new UserDTO();
        user.setId(1);
        user.setName("user name");
        user.setUsername("user username");
        user.setEmail("user email");
        user.setAddress(address1);
        user.setPhone("user phone");
        user.setWebsite("user website");
        user.setCompany(new CompanyDTO());
        users.add(user);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        ParameterizedTypeReference<List<UserDTO>> typeRef = new ParameterizedTypeReference<List<UserDTO>>() {};
        ResponseEntity<List<UserDTO>> myEntity = new ResponseEntity<>(users, HttpStatus.OK);
        when(restTemplate
                .exchange(USERS_URL, HttpMethod.GET, entity, typeRef))
                .thenReturn(myEntity);
        
        List<UserDTO> response = userService.getUsers();
        
        assertEquals(users.get(0).getId(), response.get(0).getId());
    }
    
    @Test
    public void getUserByIdTest() {
        String userId = "1";
        GeoDTO geo1 = new GeoDTO();
        geo1.setLat("lat");
        geo1.setLng("lng");
        AddressDTO address1 = new AddressDTO();
        address1.setStreet(address.getStreet());
        address1.setCity(address.getCity());
        address1.setSuite(address.getSuite());
        address1.setZipcode(address.getZipcode());
        address1.setGeo(address.getGeo());
        CompanyDTO company1 = new CompanyDTO();
        company1.setName(company.getName());
        company1.setCatchPhrase(company.getCatchPhrase());
        company1.setBs(company.getBs());
        
        UserDTO user = new UserDTO(1, "user name", "user username", 
                "user email", address, "user phone", "user website", company1);
        
        String url = USERS_URL + "/" + userId;
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        when(restTemplate
                .exchange(url, HttpMethod.GET, entity, UserDTO.class))
                .thenReturn(new ResponseEntity<>(user, HttpStatus.OK));
        
        UserDTO response = userService.getUserById(userId);
        
        assertEquals(user.getId(), response.getId());
    }
}
