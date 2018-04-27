package com.fundoonotes.exception;

public class EmptyToken extends RuntimeException
{
   private static final long serialVersionUID = 1L;

   public EmptyToken()
   {
      super("Token not accessible..");
     
   }

   
}
