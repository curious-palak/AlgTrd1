package com.todo.notes.dao;

import com.todo.notes.model.Notes;

public interface INotesDao {

	boolean createNotes(Notes notes);
	
	boolean deleteNotes(int noteId);
	
	boolean updateNotes(int noteId,Notes notes);

	Notes getNotesById(int noteId);
}
