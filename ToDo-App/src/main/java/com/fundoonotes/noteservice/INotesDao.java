package com.fundoonotes.noteservice;

import java.util.List;

import com.fundoonotes.userservice.User;

/**
 * Purpose: This is NotesDao Interface,contains defined methods, and this layer
 * responsible for interacting with Database.
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */
public interface INotesDao {

	boolean createNotes(Notes notes);
	
	boolean deleteNotes(int noteId);
	
	boolean updateNotes(int noteId,Notes notes);

	//Notes getNotesById(int noteId);

	List<Notes> getNotes(User user);

   Notes getNote(int id);

   Notes getNoteById(int noteId);
   
   //List<Notes> getNotesById(User user);
}
