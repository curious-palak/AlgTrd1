package com.fundoonotes.noteservice;

import com.fundoonotes.userservice.User;

public interface INotesService {

	//void createNote(Notes note);

	void deleteNotes(int noteId);

	void updateNotes(int noteId,Notes notes);

	void createNote(Notes notes, User user);

	Notes getNotesById(int noteId);
}
