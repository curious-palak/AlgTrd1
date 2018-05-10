package com.fundoonotes.noteservice;

import com.fundoonotes.userservice.User;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.Criteria;
import org.hibernate.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
   public boolean createNotes(Note notes)
   {
      session = sessionFactory.getCurrentSession();
      session.save(notes);
      return true;
   }

   @Override
   public boolean deleteNotes(int noteId)
   {

      session = sessionFactory.getCurrentSession();
      try {
         String deleteNote = "delete from Note where noteId=:noteId";
         Query query = session.createQuery(deleteNote);
         query.setParameter("noteId", noteId);
         query.executeUpdate();

      } catch (Exception e) {
         e.printStackTrace();
      }
      return true;
   }

   @Override
   public boolean updateNotes(int noteId, Note notes)
   {
      session = sessionFactory.getCurrentSession();

      try {
         String updateNotes = "update Note set title= :title, inTrash=:inTrash, "
                              + "isArchive=:isArchive, isPin=:isPin, color=:color," + 
                              " reminder=:reminder where noteId= :noteId";

         Query query = session.createQuery(updateNotes);
         query.setParameter("title", notes.getTitle());
         query.setParameter("inTrash", notes.getInTrash());
         query.setParameter("noteId", noteId);
         query.setParameter("isArchive", notes.getIsArchive());
         query.setParameter("isPin", notes.getIsPin());
         query.setParameter("color", notes.getColor());
         query.setParameter("reminder", notes.getReminder());
        
         /* query.setParameter("label", notes.getLabel()); */

         System.out.println("Title->>" + notes.getTitle() + ".." + "Desc.->>" + notes.getDescription() + ".."
               + "In Trash->>" + notes.getInTrash() + ".." + "IsArchiev->>" + notes.getIsArchive() + ".." + "IsPin->>"
               + notes.getIsPin() + ".." + "color->>" + notes.getColor() + ".." + "reminder->>" + notes.getReminder()
               + "..");

         query.executeUpdate();

      } catch (Exception e) {
         e.printStackTrace();
      }
      return true;
   }

   @Override
   public List<Note> getNotes(User user)
   {
      session = sessionFactory.getCurrentSession();

      Criteria criteria = session.createCriteria(Note.class);
      criteria.createAlias("user", "u", JoinType.INNER_JOIN);
      criteria.add(Restrictions.eq("u.userId", user.getUserId())).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

      List<Note> notes = criteria.list();
      return notes;
   }

   @Override
   public Note getNote(int id)
   {
      session = sessionFactory.getCurrentSession();
      Criteria criteria = session.createCriteria(Note.class);
      criteria.add(Restrictions.idEq(id));
      Note notes = (Note) criteria.uniqueResult();
      return notes;
   }

   @Override
   public Note getNoteById(int noteId)
   {
      System.out.println("In delete Dao...");
      return (Note) sessionFactory.getCurrentSession().get(Note.class, noteId);
   }

   @Override
   public void createLabel(Label label)
   {
      System.out.println("In label dao..");
      session = sessionFactory.getCurrentSession();
      session.save(label);

   }

   @Override
   public List<Label> getLabel(User user)
   {
      session = sessionFactory.getCurrentSession();

      Criteria criteria = session.createCriteria(Label.class);
      criteria.createAlias("user", "u", JoinType.INNER_JOIN);
      criteria.add(Restrictions.eq("u.userId", user.getUserId())).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

      List<Label> label = criteria.list();
      return label;
   }

   @Override
   public boolean deleteLabel(int labelId)
   {
      session = sessionFactory.getCurrentSession();
      String deleteNote = "delete from Label where labelId=:labelId";
      Query query = session.createQuery(deleteNote);
      query.setParameter("labelId", labelId);
      query.executeUpdate();
      return true;
   }

   @Override
   public void updateLabel(int labelId, Label label)
   {
      session = sessionFactory.getCurrentSession();
      String updateLabel = "update Label set labelTitle=:labelTitle where labelId=:labelId";
      
      Query query = session.createQuery(updateLabel);
      query.setParameter("labelTitle", label.getLabelTitle());
      query.setParameter("labelId", labelId);
      query.executeUpdate();
   }

   @Override
   public Label getLabelById(int labelId)
   {
      System.out.println("In Label Dao..");
      return (Label) sessionFactory.getCurrentSession().get(Label.class, labelId);
   }

   @Override
   public boolean createCollaborator(Collaborator collaborator)
   {
      session = sessionFactory.getCurrentSession();
      session.save(collaborator);
      return true;
   }

   @Override
   public int deleteCollaborator(Collaborator collaborator)
   {
      int id=0;
      session= sessionFactory.getCurrentSession();
      String deleteQuery="delete from Collaborator where note=:note and sharedUser = :sharedUser";
      Query query=session.createQuery(deleteQuery);
      query.setParameter("note", collaborator.getNote());
      query.setParameter("sharedUser", collaborator.getSharedUser());
      
      id= query.executeUpdate();
      return id;
   }
}
