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
   
   private String frontendErr;
   
   private String resetPassUrl;

   /*@Value("${mail.user}")
   private String mailuser;

   private String mailpassword;*/

   public String getFrontendHost()
   {
      return frontendHost;
   }

   public void setFrontendHost(String frontendHost)
   {
      this.frontendHost = frontendHost;
   }

   public String getFrontendErr()
   {
      return frontendErr;
   }

   public void setFrontendErr(String frontendErr)
   {
      this.frontendErr = frontendErr;
   }

   public String getResetPassUrl()
   {
      return resetPassUrl;
   }

   public void setResetPassUrl(String resetPassUrl)
   {
      this.resetPassUrl = resetPassUrl;
   }

   @Override
   public String toString()
   {
      return "Dev [frontendHost=" + frontendHost + ", frontendErr=" + frontendErr + ", resetPassUrl=" + resetPassUrl
            + "]";
   }
   
}
