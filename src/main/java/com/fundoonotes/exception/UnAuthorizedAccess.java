package com.fundoonotes.exception;

/**
 * Purpose: This class throws an exceptions if any unauthorized user try to access
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */

public class UnAuthorizedAccess extends RuntimeException
{

   private static final long serialVersionUID = 1L;

   public UnAuthorizedAccess()
   {
      super("Unauthorized Access..");

   }

}
