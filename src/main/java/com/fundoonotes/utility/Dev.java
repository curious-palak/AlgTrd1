package com.fundoonotes.utility;

import org.springframework.stereotype.Component;

/**
 * Purpose: This class is to set and get development properties
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */
@Component
public class Dev
{
   private String frontendHost;
   
   private String frontendErr;
   
   private String resetPassUrl;

   //@Value("${mail.user}")
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
      return "Dev [frontendHost=" + frontendHost + ", frontendErr=" + frontendErr + ", resetPassUrl=" + resetPassUrl
            + ", mailuser=" + mailuser + ", mailpassword=" + mailpassword + "]";
   }
   
}
