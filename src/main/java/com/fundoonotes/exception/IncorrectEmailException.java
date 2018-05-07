package com.fundoonotes.exception;

/**
 * Purpose: This class handles exceptions if Email ID is incorrect
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */

public class IncorrectEmailException extends RuntimeException
{

   private static final long serialVersionUID = 1L;

   public IncorrectEmailException()
   {
      super("Incorrect data..");
      
   }

}
