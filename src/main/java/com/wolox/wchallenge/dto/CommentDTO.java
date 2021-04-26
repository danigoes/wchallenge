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
public class CommentDTO {
    private Integer id;
    private Integer postId;
    private String name;
    private String email;
    private String body;
}
