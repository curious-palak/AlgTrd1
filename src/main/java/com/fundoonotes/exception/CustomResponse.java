package com.fundoonotes.exception;

import java.io.Serializable;

/**
 * Purpose: This model class contains two fields errorMessage,statusCode and
 * setter getters
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 *
 */
//@Component("customResponse")
//@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
// Not working as of now

public class CustomResponse implements Serializable 
{
   private static final long serialVersionUID = 1L;
   
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
