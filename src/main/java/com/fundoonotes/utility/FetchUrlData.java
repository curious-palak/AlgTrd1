package com.fundoonotes.utility;

public class FetchUrlData
{
   private String title;
   private String image;
   private String domain;
   
   public FetchUrlData(String title, String image, String domain)
   {
      this.title = title;
      this.image = image;
      this.domain = domain;
   }
   
   public String getTitle()
   {
      return title;
   }
   public void setTitle(String title)
   {
      this.title = title;
   }
   public String getImage()
   {
      return image;
   }
   public void setImage(String image)
   {
      this.image = image;
   }
   public String getDomain()
   {
      return domain;
   }
   public void setDomain(String domain)
   {
      this.domain = domain;
   }
   
}
