package com.guiconte.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.guiconte.ApiErrors;
import com.guiconte.exception.ClienteNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiErrors handleRegraNegocioException(MethodArgumentNotValidException ex){
    List<String> errors = ex.getBindingResult()
                            .getAllErrors()
                            .stream()
                            .map( error -> error.getDefaultMessage())
                            .collect(Collectors.toList());

    return new ApiErrors(errors);
  }

  @ExceptionHandler(ClienteNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ApiErrors handleClienteNotFoundException (ClienteNotFoundException ex){
    return new ApiErrors(ex.getMessage());
  }

  @ExceptionHandler(InvalidFormatException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiErrors handleHttpMessageNotReadableException (InvalidFormatException ex){
//    if(ex.getCause().getMessage().contains("Invalid date") ||
//        ex.getCause().getMessage().contains("MonthOfYear") ||
//        ex.getCause().getMessage().contains("DayOfMonth")){
//      return new ApiErrors("Data de nascimento invalida!");
//    }
    return new ApiErrors(ex.getCause().getMessage());
  }

}
