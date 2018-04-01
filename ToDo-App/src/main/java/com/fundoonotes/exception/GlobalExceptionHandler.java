package com.fundoonotes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler
{

   @ExceptionHandler(value = EmailAlreadyExistsException.class)
   public ResponseEntity<CustomResponse> emailAlreadyExistsExceptionHandler(EmailAlreadyExistsException e) {
      
      CustomResponse response = new CustomResponse();
      response.setMessage("Email already exist..");
      return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
   }

   @ExceptionHandler(value = DatabaseException.class)
   public ResponseEntity<CustomResponse> databaseExceptionHandler(DatabaseException e) {
      
     CustomResponse response =new CustomResponse();
     response.setMessage("DataBAse exception..");
   return new ResponseEntity<CustomResponse>(response,HttpStatus.CONFLICT);
   }
   
   @ExceptionHandler(value = RuntimeException.class)
   public ResponseEntity<CustomResponse> runtimeHandler(RuntimeException e) {
      
      CustomResponse response = new CustomResponse();
      response.setMessage("Run time Exception..");
      response.setStatusCode(-1);
      return new ResponseEntity<CustomResponse>(response, HttpStatus.CONFLICT);
   }
}
