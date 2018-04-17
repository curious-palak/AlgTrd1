package com.fundoonotes.noteservice;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundoonotes.exception.UnAuthorizedAccess;
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
   public void deleteNotes(int noteId, int userId)
   {
      System.out.println("In delete service..");
      User user=userDao.getUserById(userId);
      
      Notes note=notesDao.getNoteById(noteId);
      if(user.getUserId()!=note.getUser().getUserId()) {
         throw new UnAuthorizedAccess();
      }
      notesDao.deleteNotes(noteId);
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

}
