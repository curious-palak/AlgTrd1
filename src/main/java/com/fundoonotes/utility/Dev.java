package com.fundoonotes.utility;

/**
 * Purpose: This class is to set and get development properties
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */
public class Dev
{
   private String frontendHost;
   
   private String email;
   
   private String password;

   public String getFrontendHost()
   {
      return frontendHost;
   }

   public void setFrontendHost(String frontendHost)
   {
      this.frontendHost = frontendHost;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }
}
