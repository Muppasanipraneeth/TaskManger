package com.example.Task.Manger.exception;

import com.example.Task.Manger.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFound exception, WebRequest request){
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                HttpStatus.NOT_FOUND.getReasonPhrase()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
