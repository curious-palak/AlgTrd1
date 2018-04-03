package com.fundoonotes.exception;

/**
 * Purpose: This class handles exceptions if email already exists
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */

//@ResponseStatus(value=HttpStatus.RESET_CONTENT) 
public class EmailAlreadyExistsException extends RuntimeException
{

   private static final long serialVersionUID = 1L;

   public EmailAlreadyExistsException()
   {
      super("Email already registered");
   }

}