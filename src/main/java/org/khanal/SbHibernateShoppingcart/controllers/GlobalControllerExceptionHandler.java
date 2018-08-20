package org.khanal.SbHibernateShoppingcart.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleConflict() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
