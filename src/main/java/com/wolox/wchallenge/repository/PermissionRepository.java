/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.repository;

import com.wolox.wchallenge.model.Permission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daniela
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    List<Permission> findByAlbumIdAndTypePermissionId(Integer albumId, Integer typePermissionId);
}
