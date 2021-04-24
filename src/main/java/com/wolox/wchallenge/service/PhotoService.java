/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.service;

import com.wolox.wchallenge.dto.PhotoDTO;
import java.util.List;

/**
 *
 * @author Daniela
 */
public interface PhotoService {
    List<PhotoDTO> getPhotos();
    List<PhotoDTO> getPhotosByAlbumId(String albumId);
    List<PhotoDTO> getPhotosByUserId(String userId);
}
