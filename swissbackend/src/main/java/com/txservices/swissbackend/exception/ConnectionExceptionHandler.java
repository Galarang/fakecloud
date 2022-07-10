package com.txservices.swissbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.net.UnknownHostException;
import java.time.LocalDateTime;

/**
 * ConnectionExceptionHandler is exception handler class, potential exceptions are handled here
 *
 * @author Dusan Batinica
 *
 */

@ControllerAdvice
public class ConnectionExceptionHandler {

    @ExceptionHandler(value = {ConnectionException.class})
    public ResponseEntity<ConnectionErrorResponse> handleConnectionException(ConnectionException connectionException) {
        ConnectionErrorResponse connectionErrorResponse = new ConnectionErrorResponse();
        connectionErrorResponse.setMessage(connectionException.getMessage());
        connectionErrorResponse.setTimestamp(LocalDateTime.now());
        connectionErrorResponse.setStatus(HttpStatus.OK); //since there are no results but request is OK
        return new ResponseEntity<>(connectionErrorResponse, connectionErrorResponse.getStatus());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ConnectionErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        ConnectionErrorResponse connectionErrorResponse = new ConnectionErrorResponse();
        connectionErrorResponse.setMessage(methodArgumentNotValidException.getMessage());
        connectionErrorResponse.setTimestamp(LocalDateTime.now());
        connectionErrorResponse.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(connectionErrorResponse, connectionErrorResponse.getStatus());
    }

    @ExceptionHandler(value = {HttpClientErrorException.class})
    public ResponseEntity<ConnectionErrorResponse> handleHttpClientErrorException(HttpClientErrorException httpClientErrorException) {
        ConnectionErrorResponse connectionErrorResponse = new ConnectionErrorResponse();
        connectionErrorResponse.setMessage(httpClientErrorException.getResponseBodyAsString());
        connectionErrorResponse.setTimestamp(LocalDateTime.now());
        connectionErrorResponse.setStatus(httpClientErrorException.getStatusCode());
        return new ResponseEntity<>(connectionErrorResponse, connectionErrorResponse.getStatus());
    }

    @ExceptionHandler(value = {UnknownHostException.class})
    public ResponseEntity<ConnectionErrorResponse> handleUnknownHostException(UnknownHostException unknownHostException) {
        ConnectionErrorResponse connectionErrorResponse = new ConnectionErrorResponse();
        connectionErrorResponse.setMessage("Swiss Public Transport API is currently down...");
        connectionErrorResponse.setTimestamp(LocalDateTime.now());
        connectionErrorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(connectionErrorResponse, connectionErrorResponse.getStatus());
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<ConnectionErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException handHttpMessageNotReadableException) {
        ConnectionErrorResponse connectionErrorResponse = new ConnectionErrorResponse();
        connectionErrorResponse.setMessage("Transportation that you chose doesn't exist");
        connectionErrorResponse.setTimestamp(LocalDateTime.now());
        connectionErrorResponse.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(connectionErrorResponse, connectionErrorResponse.getStatus());
    }


}
