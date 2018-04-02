package com.fundoonotes.exception;

/**Purpose: This class handles dataBase exceptions
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */
public class DatabaseException extends RuntimeException
{
   private static final long serialVersionUID = 1L;

   public DatabaseException()
   {
      super("Error in database operation");
   }

}
