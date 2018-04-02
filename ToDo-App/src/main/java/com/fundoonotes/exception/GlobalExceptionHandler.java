package com.fundoonotes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Purpose:This class handles all the exceptions
 * 
 * {@link @ControllerAdvice} apply globally to all Controllers, and handles the
 * exceptions
 * 
 * {@link @ExceptionHandler} to handles different exceptions
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */
@ControllerAdvice
public class GlobalExceptionHandler
{

   CustomResponse response = new CustomResponse();
   
   @ExceptionHandler(value = EmailAlreadyExistsException.class)
   public ResponseEntity<CustomResponse> emailAlreadyExistsExceptionHandler(EmailAlreadyExistsException e)
   {
      
      response.setMessage("Email already exist..");
      return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
   }

   @ExceptionHandler(value = DatabaseException.class)
   public ResponseEntity<CustomResponse> databaseExceptionHandler(DatabaseException e)
   {
      
      response.setMessage("DataBAse exception..");
      return new ResponseEntity<CustomResponse>(response, HttpStatus.CONFLICT);
   }

   @ExceptionHandler(value = RuntimeException.class)
   public ResponseEntity<CustomResponse> runtimeHandler(RuntimeException e)
   {

      response.setMessage("Something went wrong..");
      response.setStatusCode(-1);
      return new ResponseEntity<CustomResponse>(response, HttpStatus.CONFLICT);
   }
}
