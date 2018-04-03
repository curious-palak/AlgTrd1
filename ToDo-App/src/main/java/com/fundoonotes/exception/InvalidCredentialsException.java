package com.fundoonotes.exception;

public class InvalidCredentialsException extends RuntimeException
{
   private static final long serialVersionUID = 1L;
   
   public InvalidCredentialsException() {
      
      super("Invalid Login Credentials..");
   }
}
