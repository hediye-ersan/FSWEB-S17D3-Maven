package com.workintech.zoo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@RestControllerAdvice
public class ZooGlobalExceptionHandler {

    @ExceptionHandler(ZooException.class)
    public ResponseEntity<ZooErrorResponse> handleZooException(ZooException zooException){
        ZooErrorResponse errorResponse = new ZooErrorResponse(zooException.getMessage(),zooException.getHttpStatus().value(),System.currentTimeMillis());
            log.error("ZooException occurred: {}", zooException.getMessage());
        return new ResponseEntity<>(errorResponse, zooException.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ZooErrorResponse> handleGeneralException(Exception exception){
    ZooErrorResponse errorResponse = new ZooErrorResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),System.currentTimeMillis());
        log.error("General Exception occurred: {}", exception.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
