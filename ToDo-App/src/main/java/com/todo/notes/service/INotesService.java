package com.todo.notes.service;

import com.todo.notes.model.Notes;
import com.todo.users.model.User;

public interface INotesService {

	//void createNote(Notes note);

	void deleteNotes(int noteId);

	void updateNotes(int noteId,Notes notes);

	void createNote(Notes notes, User user);

	Notes getNotesById(int noteId);
}
