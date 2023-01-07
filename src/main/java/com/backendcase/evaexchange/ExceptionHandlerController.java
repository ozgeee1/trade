package com.backendcase.evaexchange;

import com.backendcase.evaexchange.error.ApiError;
import com.backendcase.evaexchange.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { BadRequestException.class})
    public ResponseEntity<ApiError> handleBadCredentialsException(BadRequestException ex){
        ApiError error = new ApiError();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error,error.getStatus());

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError>  constraintValidationException(MethodArgumentNotValidException ex){
        ApiError apiError = new ApiError();
        List<String> fieldErrors = new ArrayList<>();
        ex.getFieldErrors().stream().forEach(fieldError -> fieldErrors.add("Field:"+fieldError.getField()+"       "+"Message:"+fieldError.getDefaultMessage()));
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setMessage(fieldErrors.size()!= 0 ? null : ex.getMessage());
        apiError.setErrors(fieldErrors);
        apiError.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }




}
