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
      response.setStatusCode(201);
      return new ResponseEntity<>(response, HttpStatus.CREATED);
   }

   @ExceptionHandler(value = EmailIdNotExists.class)
   public ResponseEntity<CustomResponse> emailIdNotExists(EmailIdNotExists e)
   {
      response.setMessage("Email Id not exists");
      response.setStatusCode(404);
      return new ResponseEntity<CustomResponse>(response, HttpStatus.NOT_FOUND);
   }

   @ExceptionHandler(value = InvalidCredentialsException.class)
   public ResponseEntity<CustomResponse> invalidCredentials(InvalidCredentialsException e)
   {
      response.setMessage("Invalid Login Credentials..");
      response.setStatusCode(409);
      return new ResponseEntity<CustomResponse>(response, HttpStatus.CONFLICT);

   }

   @ExceptionHandler(value = RegistrationValidationException.class)
   public ResponseEntity<CustomResponse> ValidationException(RegistrationValidationException e)
   {
      response.setMessage("User Validation Error..");
      response.setStatusCode(400);
      return new ResponseEntity<CustomResponse>(response, HttpStatus.BAD_REQUEST);
   }

   @ExceptionHandler(value = IncorrectEmailException.class)
   public ResponseEntity<CustomResponse> incorrectDataException(IncorrectEmailException e)
   {
      response.setMessage("Enter a valid email ID,that is registered..");
      response.setStatusCode(403);
      return new ResponseEntity<CustomResponse>(response, HttpStatus.FORBIDDEN);
   }

   @ExceptionHandler(value = DatabaseException.class)
   public ResponseEntity<CustomResponse> databaseExceptionHandler(DatabaseException e)
   {

      response.setMessage("DataBAse exception..");
      response.setStatusCode(409);
      return new ResponseEntity<CustomResponse>(response, HttpStatus.CONFLICT);
   }

   @ExceptionHandler(value = EmptyToken.class)
   public ResponseEntity<CustomResponse> unAccessibleToken(EmptyToken e)
   {

      response.setMessage("Token not accessible please,enter a token to add notes");
      response.setStatusCode(410);
      return new ResponseEntity<CustomResponse>(response, HttpStatus.BAD_REQUEST);
   }

   @ExceptionHandler(value = UnAuthorizedAccess.class)
   public ResponseEntity<CustomResponse> unauthorizedAccess(UnAuthorizedAccess e)
   {
      response.setMessage("UnAuthorized Access..");
      response.setStatusCode(410);
      return new ResponseEntity<CustomResponse>(response, HttpStatus.BAD_REQUEST);

   }

   @ExceptionHandler(value = RuntimeException.class)
   public ResponseEntity<CustomResponse> runtimeHandler(RuntimeException e)
   {
      e.printStackTrace();
      response.setMessage("Something went wrong,try again later");
      response.setStatusCode(-1);
      return new ResponseEntity<CustomResponse>(response, HttpStatus.CONFLICT);
   }
}
