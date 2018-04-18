package com.fundoonotes.noteservice;

import java.util.List;

/**
 * Purpose: This is NotesService Interface,contains defined methods, This layer
 * interact with controller.
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */
public interface INotesService
{
   
   void createNote(Notes notes, int userId);

   //boolean deleteNotes(int noteId,int userId);
   
   List<Notes> getNotes(int id);

   void updateNotes(int noteId, Notes notes, int userId);

   boolean deleteNotes(Notes note, int id);
}
