package com.Alzain.Drones.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {
    @ExceptionHandler
    public ResponseEntity<ErrorModel> handleExcption(Exception ex){
        ErrorModel error = new ErrorModel().builder()
                .message(ex.getMessage())
                .status(HttpStatus.FORBIDDEN.value())
                .timestamp(System.currentTimeMillis()).build();
        return new ResponseEntity<>(error,HttpStatus.FORBIDDEN);
    }
}
