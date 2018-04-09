package com.fundoonotes.noteservice;

/**
 * Purpose: This is NotesService Interface,contains defined methods, This layer
 * interact with controller.
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */
public interface INotesService
{

   // void createNote(Notes note);

   void deleteNotes(int noteId);

   void updateNotes(int noteId, Notes notes);

   void createNote(Notes notes, int userId);

   Notes getNotesById(int noteId);
}
