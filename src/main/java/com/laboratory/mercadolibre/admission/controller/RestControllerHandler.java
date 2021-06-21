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
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class RestControllerHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request) {
        log.error("handleAllExceptions - exception:" + exception);
        Map<String, Object> responseMap = new HashMap<String, Object>();
        responseMap.put("status", HttpStatus.NOT_FOUND);
        responseMap.put("error", exception.getMessage());
        responseMap.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
    }

}
