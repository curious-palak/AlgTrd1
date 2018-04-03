package com.fundoonotes.exception;

public class EmailIdNotExists extends RuntimeException
{
   private static final long serialVersionUID = 1L;

   public EmailIdNotExists()
   {
      super("Email not exists..");
     
   }

   
}
