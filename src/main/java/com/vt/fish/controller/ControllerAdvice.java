package com.vt.fish.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.client.WebClientException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Vibrant Tropical backend service error")
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void handleHttpMessageNotReadableException() {
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error communicating with 3rd Party Service")
    @ExceptionHandler(WebClientException.class)
    public void handleWebClientException() {
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error")
    @ExceptionHandler(Exception.class)
    public void handleException(Exception e) {
    }
}
