/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.exception;

/**
 *
 * @author Daniela
 */
public class ApiRequestException extends RuntimeException {
    
    public ApiRequestException(String message) {
        super(message);
    }
    
    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
