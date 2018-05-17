package com.fundoonotes.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Purpose: This class is to set and get development properties
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */

public class Dev
{
   private String frontendHost;

   @Value("${mail.user}")
   private String mailuser;

   private String mailpassword;

   public String getFrontendHost()
   {
      return frontendHost;
   }

   public void setFrontendHost(String frontendHost)
   {
      this.frontendHost = frontendHost;
   }

   public String getMailuser()
   {
      return mailuser;
   }

   public void setMailuser(String mailuser)
   {
      this.mailuser = mailuser;
   }

   public String getMailpassword()
   {
      return mailpassword;
   }

   public void setMailpassword(String mailpassword)
   {
      this.mailpassword = mailpassword;
   }

   @Override
   public String toString()
   {
      return "Dev [ mailuser=" + mailuser + ", mailpassword=" + mailpassword + "]";
   }

}
