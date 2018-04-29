package com.fundoonotes.noteservice;

import java.util.List;

public class NoteResponseDto
{
   private List<NoteDto> notes;

   public List<NoteDto> getNotes()
   {
      return notes;
   }

   public void setNotes(List<NoteDto> notes)
   {
      this.notes = notes;
   }

}
