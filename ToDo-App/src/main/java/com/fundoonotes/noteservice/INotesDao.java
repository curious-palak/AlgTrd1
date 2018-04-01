package com.fundoonotes.noteservice;

public interface INotesDao {

	boolean createNotes(Notes notes);
	
	boolean deleteNotes(int noteId);
	
	boolean updateNotes(int noteId,Notes notes);

	Notes getNotesById(int noteId);
}
