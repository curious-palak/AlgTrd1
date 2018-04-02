package com.fundoonotes.noteservice;

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

	Notes getNotesById(int noteId);
}
