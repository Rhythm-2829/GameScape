package com.rhythm.gamestore.GameStore.utility;

import com.rhythm.gamestore.GameStore.exception.GameStoreException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerException {

    @ExceptionHandler(GameStoreException.class)
    public ResponseEntity<Error> customException(GameStoreException e) {
        Error error = new Error();
        List<String> errMsg = new ArrayList<>();
        errMsg.add(e.getMessage());
        error.setMessage(errMsg);
        error.setStatusCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
        error.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        Error error = new Error();
        error.setMessage(errors);
        error.setStatusCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        error.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
