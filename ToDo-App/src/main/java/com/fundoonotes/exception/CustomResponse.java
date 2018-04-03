package com.fundoonotes.exception;

/**
 * Purpose: This model class contains two fields errorMessage,statusCode and
 * setter getters
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 *
 */
public class CustomResponse
{

   private String errorMessage;
   private int statusCode;

   public String getMessage()
   {
      return errorMessage;
   }

   public void setMessage(String errorMessage)
   {
      this.errorMessage = errorMessage;
   }

   public int getStatusCode()
   {
      return statusCode;
   }

   public void setStatusCode(int statusCode)
   {
      this.statusCode = statusCode;
   }
}
