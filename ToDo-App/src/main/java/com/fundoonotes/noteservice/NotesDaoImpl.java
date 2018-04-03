package com.fundoonotes.noteservice;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;

/**
 * Purpose:This class contains implementation of notesDao interface and contains
 * methods to perform database operations.
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */
@Repository
public class NotesDaoImpl implements INotesDao {

	@Autowired
	SessionFactory sessionFactory;

	public Session session;

	@Override
	public boolean createNotes(Notes notes) {

		session = sessionFactory.openSession();
		session.save(notes);
		return true;
	}

	@Override
	public boolean deleteNotes(int noteId) {

		session = sessionFactory.openSession();
		try {
			String deleteNote = "delete from Notes where noteId=:noteId";
			Query query = session.createQuery(deleteNote);
			query.setParameter("noteId", noteId);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean updateNotes(int noteId, Notes notes) {
		session = sessionFactory.openSession();
		try {
			String updateNotes = "update Notes set title= :title where noteId= :noteId";
			Query query = session.createQuery(updateNotes);
			query.setParameter("noteId", noteId);
			
			query.setParameter("title", notes.getTitle());
			/*System.out.println(noteId);
			System.out.println(notes.getTitle());*/
			query.executeUpdate();
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public Notes getNotesById(int noteId) {
		
		return (Notes) sessionFactory.openSession().get(Notes.class, noteId);
	}
}
