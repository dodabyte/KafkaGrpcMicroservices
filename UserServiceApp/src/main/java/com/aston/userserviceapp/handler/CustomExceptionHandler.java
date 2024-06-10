package com.aston.userserviceapp.handler;

import com.aston.userserviceapp.dto.exception.ErrorDto;
import com.aston.userserviceapp.exception.EntityNotFoundException;
import com.aston.userserviceapp.exception.InsertionException;
import com.aston.userserviceapp.exception.JsonException;
import com.aston.userserviceapp.exception.RepositoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class CustomExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> exception(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> notFound(EntityNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(InsertionException.class)
    public ResponseEntity<ErrorDto> badRequest(InsertionException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(RepositoryException.class)
    public ResponseEntity<ErrorDto> internalServer(RepositoryException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(JsonException.class)
    public ResponseEntity<ErrorDto> notAcceptable(JsonException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ErrorDto(exception.getMessage()));
    }
}
