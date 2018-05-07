package com.fundoonotes.exception;

/**
 * Purpose: This class handles exceptions if credentials are invalid
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */

public class InvalidCredentialsException extends RuntimeException
{
   private static final long serialVersionUID = 1L;

   public InvalidCredentialsException()
   {

      super("Invalid Login Credentials..");
   }
}
