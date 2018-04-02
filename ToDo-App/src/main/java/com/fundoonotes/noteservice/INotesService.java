package com.fundoonotes.noteservice;

import com.fundoonotes.userservice.User;

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

   void createNote(Notes notes, User user);

   Notes getNotesById(int noteId);
}
