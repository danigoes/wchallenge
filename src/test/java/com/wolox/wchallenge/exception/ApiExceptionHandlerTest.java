/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wolox.wchallenge.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Daniela
 */
public class ApiExceptionHandlerTest {
    private final ApiExceptionHandler handler = new ApiExceptionHandler() {
    };

    @Test
    public void handleApiRequestExceptionTest() throws Exception {

        final ResponseEntity<Object> handled = handler.handleApiRequestException(new ApiRequestException(""));
        assertEquals(HttpStatus.BAD_REQUEST, handled.getStatusCode());
    }
    
    @Test
    public void handleAllExceptionsTest() throws Exception {
        ApiException exception = new ApiException();
        exception.setMessage("");
        exception.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        exception.setTimestamp(ZonedDateTime.now(ZoneId.of("Z")));
        final ResponseEntity<Object> handled = handler.handleAllExceptions(new Exception(""));
        assertEquals(exception.getHttpStatus(), handled.getStatusCode());
    }
    
    @Test
    public void handleDataNotFoundExceptionTest() throws Exception {
        ApiException exceptionTest = new ApiException("", HttpStatus.NOT_FOUND, ZonedDateTime.now(ZoneId.of("Z")));
        ApiException exception = new ApiException();
        exception.setMessage(exceptionTest.getMessage());
        exception.setHttpStatus(exceptionTest.getHttpStatus());
        exception.setTimestamp(exceptionTest.getTimestamp());
        final ResponseEntity<Object> handled = handler.handleDataNotFoundException(new DataNotFoundException(""));
        assertEquals(HttpStatus.NOT_FOUND, handled.getStatusCode());
    }
}
