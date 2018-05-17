package com.fundoonotes.exception;

/**
 * Purpose: This class handles exceptions if token is null
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */

public class EmptyToken extends RuntimeException
{
   private static final long serialVersionUID = 1L;

   public EmptyToken()
   {
      super("Token not accessible..");
     
   }

   
}
