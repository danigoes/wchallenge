/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Daniela
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApiRequestException extends RuntimeException {
    
    public ApiRequestException(String message) {
        super(message);
    }
  
}
