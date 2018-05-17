package com.fundoonotes.exception;

/**
 * Purpose: This class handles exceptions occurs at the time of registration
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */

public class RegistrationValidationException extends RuntimeException
{
   private static final long serialVersionUID = 1L;

   public RegistrationValidationException()
   {
      super("User Validation exception..");
   }
}
