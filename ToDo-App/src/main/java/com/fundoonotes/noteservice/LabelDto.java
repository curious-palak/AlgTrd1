package com.fundoonotes.noteservice;

import com.fundoonotes.userservice.User;

public class LabelDto   
{  
   private int labelId;
   private String labelTitle;
   private User user;
   
   public int getLabelId()
   {
      return labelId;
   }
   public void setLabelId(int labelId)
   {
      this.labelId = labelId;
   }
   public String getLabelTitle()
   {
      return labelTitle;
   }
   public void setLabelTitle(String labelTitle)
   {
      this.labelTitle = labelTitle;
   }
   public User getUser()
   {
      return user;
   }
   public void setUser(User user)
   {
      this.user = user;
   }
}
