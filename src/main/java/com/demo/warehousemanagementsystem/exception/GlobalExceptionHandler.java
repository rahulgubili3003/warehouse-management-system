package com.demo.warehousemanagementsystem.exception;

import com.demo.warehousemanagementsystem.misc.JsonSerialize;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JsonStringConversionException.class)
    public ResponseEntity<String> jsonStringConversionException(JsonStringConversionException jsonStringConversionException) {
        String jsonString = JsonSerialize.exceptionJsonString(jsonStringConversionException.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonString);
    }

    @ExceptionHandler(DatabaseOperationException.class)
    public ResponseEntity<String> databaseOperationException(DatabaseOperationException databaseOperationException) {
        String jsonString = JsonSerialize.exceptionJsonString(databaseOperationException.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonString);
    }
}
