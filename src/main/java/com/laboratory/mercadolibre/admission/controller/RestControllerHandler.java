package com.laboratory.mercadolibre.admission.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestControllerAdvice
@Slf4j
public class RestControllerHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request) {
        log.error("handleAllExceptions - exception:" + exception);
        var response = new HashMap<>();
        response.put("status", HttpStatus.NOT_FOUND);
        response.put("error", exception.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
