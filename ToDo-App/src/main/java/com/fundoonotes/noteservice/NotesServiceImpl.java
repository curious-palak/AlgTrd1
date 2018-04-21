package com.fundoonotes.noteservice;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundoonotes.userservice.IUserDao;
import com.fundoonotes.userservice.User;

/**
 * Purpose: This class contains implementation of notesService interface and
 * contains business logic.
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */

@Service
public class NotesServiceImpl implements INotesService
{

   @Autowired
   INotesDao notesDao;

   @Autowired
   IUserDao userDao;

   @Transactional
   @Override
   public void createNote(Notes notes, int userId)
   {
      Date date = new Date();
      notes.setDate(date);

      User user = new User();
      user.setUserId(userId);
      notes.setUser(user);
      System.out.println("Notes->>"+notes.getInTrash()+".."+notes.getTitle()+".."+notes.getDescription());
      notesDao.createNotes(notes);
   }

   @Transactional
   @Override
   public boolean deleteNotes(Notes note, int userId)
   {
      System.out.println("In delete service..");
      
      //Notes note=notesDao.getNoteById(noteId);
      if(userId == note.getUser().getUserId()) {
        return notesDao.deleteNotes(note.getnoteId());
      }
      return false;
   }
 
   
   @Transactional
   @Override
   public void updateNotes(int noteId, Notes notes, int userId)
   {
      System.out.println(noteId);
      notesDao.updateNotes(noteId, notes);
   }

   @Transactional
   @Override
   public List<Notes> getNotes(int id)
   {
      User user = new User();
      user.setUserId(id);
      //userDao.getUserById(id);
      return notesDao.getNotes(user);
   }

   @Transactional
   @Override
   public void createLabel(Label label,int userId)
   {
      System.out.println("In label service..");
      User user = new User();
      user.setUserId(userId);
      label.setUser(user);
    
      System.out.println("labels->>"+user.getUserId()+" "+label.getLabelTitle());
      notesDao.createLabel(label); 
   }

   @Transactional
   @Override
   public List<Label> getLabel(int userId)
   {
      User user = new User();
      user.setUserId(userId);
     
      return notesDao.getLabel(user);
   }
}
