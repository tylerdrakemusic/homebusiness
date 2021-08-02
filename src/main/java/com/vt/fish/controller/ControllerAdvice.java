package com.vt.fish.controller;

import com.vt.fish.controller.special.OrderOutOfRangeException;
import com.vt.fish.model.response.SubordinateResponse;
import com.vt.fish.service.DatabaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.client.WebClientException;

@RestControllerAdvice
public class ControllerAdvice {

    private SubordinateResponse subordinateResponse = new SubordinateResponse();

    private DatabaseService databaseService;

    private ControllerAdvice(DatabaseService databaseService){
        this.databaseService = databaseService;
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class, OrderOutOfRangeException.class})
    public ResponseEntity<SubordinateResponse> handleBadRequest(RuntimeException e, WebRequest webRequest) {
        subordinateResponse.setCorrelationId(webRequest.getHeader("CorrelationId"));
        subordinateResponse.setMessage(e.getMessage());
        subordinateResponse.setVibrantTropicalRequestId((String) webRequest.getAttribute("VibrantTropicalRequestId",1));
        databaseService.saveSubordinateResponse(subordinateResponse);
        return new ResponseEntity<>(subordinateResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(WebClientException.class)
    public ResponseEntity<SubordinateResponse> handleWebClientException(WebClientException e, WebRequest webRequest) {
        subordinateResponse.setCorrelationId(webRequest.getHeader("CorrelationId"));
        subordinateResponse.setMessage(e.getMessage());
        subordinateResponse.setVibrantTropicalRequestId((String) webRequest.getAttribute("VibrantTropicalRequestId",1));
        databaseService.saveSubordinateResponse(subordinateResponse);
        return new ResponseEntity<>(subordinateResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<SubordinateResponse>  handleException(Exception e, WebRequest webRequest) {
        subordinateResponse.setCorrelationId(webRequest.getHeader("CorrelationId"));
        subordinateResponse.setMessage(e.getMessage());
        subordinateResponse.setVibrantTropicalRequestId((String) webRequest.getAttribute("VibrantTropicalRequestId",1));
        databaseService.saveSubordinateResponse(subordinateResponse);
        return new ResponseEntity<>(subordinateResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
