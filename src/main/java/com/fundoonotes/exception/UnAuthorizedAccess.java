package com.fundoonotes.exception;

public class UnAuthorizedAccess extends RuntimeException
{

   private static final long serialVersionUID = 1L;

   public UnAuthorizedAccess()
   {
      super("Unauthorized Access..");
      
   }
 
}
