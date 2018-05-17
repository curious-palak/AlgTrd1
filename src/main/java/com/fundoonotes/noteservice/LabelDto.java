package com.fundoonotes.noteservice;

public class LabelDto
{
   private int labelId;
   private String labelTitle;

   public LabelDto(Label labelObj)
   {
      this.labelId = labelObj.getLabelId();
      this.labelTitle = labelObj.getLabelTitle();
   }

   public String getLabelTitle()
   {
      return labelTitle;
   }

   public void setLabelTitle(String labelTitle)
   {
      this.labelTitle = labelTitle;
   }

   public int getLabelId()
   {
      return labelId;
   }

   public void setLabelId(int labelId)
   {
      this.labelId = labelId;
   }

}
