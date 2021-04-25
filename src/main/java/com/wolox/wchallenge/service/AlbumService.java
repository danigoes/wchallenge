/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.service;

import com.wolox.wchallenge.dto.AlbumDTO;
import java.util.List;

/**
 *
 * @author Daniela
 */
public interface AlbumService {
    List<AlbumDTO> getAlbums();
    List<AlbumDTO> getAlbumsByUserId(String userId);
    AlbumDTO getAlbumById(String albumId);
}
