package com.fundoonotes.exception;

public class RegistrationValidationException extends RuntimeException
{
   private static final long serialVersionUID = 1L;

   public RegistrationValidationException()
   {
      super("User Validation exception..");
   }
}
