package com.fundoonotes.userservice;

public class UserDTO
{
   private String name;
   private String email;
   private String password;
   private String mobileNo;
   private String randomUUId;

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
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

   public String getMobileNo()
   {
      return mobileNo;
   }

   public void setMobileNo(String mobileNo)
   {
      this.mobileNo = mobileNo;
   }

   public String getRandomUUId()
   {
      return randomUUId;
   }

   public void setRandomUUId(String randomUUId)
   {
      this.randomUUId = randomUUId;
   }
}
