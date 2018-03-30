package com.todo.notes.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.todo.notes.dao.INotesDao;
import com.todo.notes.model.Notes;
import com.todo.users.dao.IUserDao;
import com.todo.users.model.User;

@Service
public class NotesServiceImpl implements INotesService {

	@Autowired
	INotesDao notesDao;

	@Autowired
	IUserDao userDao;

	@Transactional
	@Override
	public void createNote(Notes notes, User user) {

		Date date = new Date();
		notes.setDate(date);
		notes.setUser(user);
		notesDao.createNotes(notes);
	}

	@Transactional
	@Override
	public void deleteNotes(int noteId) {
		notesDao.deleteNotes(noteId);
	}

	@Transactional
	@Override
	public void updateNotes(int noteId, Notes notes) {
		// System.out.println(noteId);
		notesDao.updateNotes(noteId, notes);
	}

	@Override
	public Notes getNotesById(int noteId) {

		return notesDao.getNotesById(noteId);
	}
}
