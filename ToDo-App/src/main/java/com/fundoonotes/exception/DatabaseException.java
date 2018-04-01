package com.fundoonotes.exception;

public class DatabaseException extends RuntimeException
{
   private static final long serialVersionUID = 1L;

   public DatabaseException()
   {
      super("Error in database operation");
   }

}
