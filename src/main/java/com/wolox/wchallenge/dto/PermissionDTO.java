/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Daniela
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDTO {
    private Integer id;
    private Integer albumId;
    private Integer userId;
    private Integer typePermissionId;
    private String typePermission;
}
