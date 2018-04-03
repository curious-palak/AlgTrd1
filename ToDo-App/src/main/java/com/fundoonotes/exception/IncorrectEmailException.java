package com.fundoonotes.exception;

public class IncorrectEmailException extends RuntimeException
{

   private static final long serialVersionUID = 1L;

   public IncorrectEmailException()
   {
      super("Incorrect data..");
      
   }

}
