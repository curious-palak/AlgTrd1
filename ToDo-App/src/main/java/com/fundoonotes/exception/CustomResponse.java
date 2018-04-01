package com.fundoonotes.exception;

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
