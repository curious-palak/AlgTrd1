package com.fundoonotes.noteservice;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.fundoonotes.userservice.User;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;

/**
 * Purpose:This class contains implementation of notesDao interface and contains
 * methods to perform database operations.
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */
@Repository
public class NotesDaoImpl implements INotesDao
{

   @Autowired
   SessionFactory sessionFactory;

   public Session session;

   @Override
   public boolean createNotes(Notes notes)
   {

      session = sessionFactory.openSession();
      session.save(notes);
      return true;
   }
   
   @Override
   public boolean deleteNotes(int noteId)
   {

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
   public boolean updateNotes(int noteId, Notes notes)
   {
      session = sessionFactory.openSession();

      try {
         String updateNotes = "update Notes set title= :title, inTrash=:inTrash where noteId= :noteId";
         Query query = session.createQuery(updateNotes);
         query.setParameter("title", notes.getTitle());
         query.setParameter("inTrash", notes.getInTrash());
         query.setParameter("noteId", noteId);
         
         System.out.println(notes.getTitle()+"....."+notes.getDescription()+"....."+notes.getInTrash());
          
         query.executeUpdate();

      } catch (Exception e) {
         e.printStackTrace();
      }
      return true;
   }

   @Override
   public List<Notes> getNotes(User user)
   {
      session = sessionFactory.getCurrentSession();

      Criteria criteria = session.createCriteria(Notes.class);
      criteria.createAlias("user", "u", JoinType.INNER_JOIN);
      criteria.add(Restrictions.eq("u.userId", user.getUserId())).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

      List<Notes> notes = criteria.list();
      return notes;
   }

   @Override
   public Notes getNote(int id)
   {
      session = sessionFactory.getCurrentSession();
      Criteria criteria = session.createCriteria(Notes.class);
      criteria.add(Restrictions.idEq(id));
      Notes notes = (Notes) criteria.uniqueResult();
      return notes;
   }

   @Override
   public Notes getNoteById(int noteId)
   {
      System.out.println("In delete Dao...");
      return (Notes) sessionFactory.getCurrentSession().get(Notes.class, noteId);

   }
}
