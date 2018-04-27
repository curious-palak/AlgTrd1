package com.fundoonotes.noteservice;

public class LabelDto   
{  
   private boolean status;
   private int labelId;
   private int noteId;
   
   public boolean isStatus()
   {
      return status;
   }
   public void setStatus(boolean status)
   {
      this.status = status;
   }
   public int getLabelId()
   {
      return labelId;
   }
   public void setLabelId(int labelId)
   {
      this.labelId = labelId;
   }
   public int getNoteId()
   {
      return noteId;
   }
   public void setNoteId(int noteId)
   {
      this.noteId = noteId;
   }
}
