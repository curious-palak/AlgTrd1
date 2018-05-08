package com.fundoonotes.noteservice;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fundoonotes.userservice.IUserDao;
import com.fundoonotes.userservice.User;
import com.fundoonotes.utility.FetchUrlData;

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
   public void createNote(Note notes, int userId)
   {
      Date date = new Date();
      notes.setDate(date);

      User user = new User();
      user.setUserId(userId);
      notes.setUser(user);
      System.out.println("Notes->>" + notes.getInTrash() + ".." + notes.getTitle() + ".." + notes.getDescription());
      notesDao.createNotes(notes);
   }

   @Transactional
   @Override
   public boolean deleteNotes(Note note, int userId)
   {
      System.out.println("In delete service..");

      // Notes note=notesDao.getNoteById(noteId);
      if (userId != 0) {
         return notesDao.deleteNotes(note.getnoteId());
      }
      return false;
   }

   @Transactional
   @Override
   public void updateNotes(int noteId, Note notes, int userId)
   {
      System.out.println(noteId);
      notesDao.updateNotes(noteId, notes);
   }

   @Transactional
   @Override
   public List<NoteDto> getNotes(int id)
   {
      User user = userDao.getUserById(id);
      // user.setUserId(id);

      List<Note> note = notesDao.getNotes(user);
      List<NoteDto> noteDto = new ArrayList<NoteDto>();

      for (Note noteObject : note) {

         NoteDto noteDtObject = new NoteDto(noteObject);
         noteDto.add(noteDtObject);
      }
      return noteDto;

   }

   @Transactional
   @Override
   public void createLabel(Label label, int userId)
   {
      System.out.println("In label service..");
      User user = new User();
      user.setUserId(userId);
      label.setUser(user);

      System.out.println("labels->>" + user.getUserId() + " " + label.getLabelTitle());
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

   @Transactional
   @Override
   public boolean deleteLabel(Label label, int id)
   {
      System.out.println("In delete LAbel service...");
      if (id == label.getUser().getUserId()) {
         return notesDao.deleteLabel(label.getLabelId());
      }
      return false;
   }

   @Transactional
   @Override
   public void updateLabel(int labelId, Label label, int userId)
   {
      System.out.println(labelId);
      notesDao.updateLabel(labelId, label);
   }

   @Transactional
   @Override
   public void addLabelOnNote(int noteId, int labelId)
   {
      Note note = notesDao.getNoteById(noteId);
      Label label = notesDao.getLabelById(labelId);
      note.getLabel().add(label);
      notesDao.updateNotes(noteId, note);
   }

   @Transactional
   @Override
   public boolean deleteLabelOnNote(int noteId, int labelId)
   {
      System.out.println("In delete label note..");
      Note note = notesDao.getNoteById(noteId);

      return false;
   }

   @Transactional
   @Override
   public void createCollaborator(CollaboratorDTO collaboratorDto, int userId)
   {

      Collaborator collaborator = new Collaborator();
      collaborator.setOwner(userDao.getUserById(userId));

      collaborator.setSharedUser(userDao.getUserByEmail(collaboratorDto.getEmail()));

      /*
       * Check here if shared user there in DB then throw exception user exist
       */
      collaborator.setNote(notesDao.getNoteById(collaboratorDto.getNoteId()));

      notesDao.createCollaborator(collaborator);
   }

   @Transactional
   @Override
   public boolean deletecollborator(CollaboratorDTO collaboratordto)
   {
      Collaborator collaborator = new Collaborator();
      collaborator.setNote(notesDao.getNoteById(collaboratordto.getNoteId()));
      collaborator.setSharedUser(userDao.getUserByEmail(collaboratordto.getEmail()));

      // Note note = collaboratordto.getNoteId();
      // User sharedUser = collaborator.getSharedUser();
      int id = notesDao.deleteCollaborator(collaborator);
      if (id > 0) {
         return true;
      } else {
         return false;
      }

   }

   @Transactional
   @Override
   public void uploadImage(MultipartFile uploadNoteImage, int userId, int noteId) throws SerialException, IOException, SQLException
   {
      User user = userDao.getUserById(userId);
      user.setUserId(userId);

      Note note = notesDao.getNoteById(noteId);
      note.setnoteId(noteId);
      if (uploadNoteImage.getBytes() != null) {
         byte[] noteImg = uploadNoteImage.getBytes();
         //Blob blob = new SerialBlob(noteImg);
         note.setNoteImage(noteImg);

         notesDao.updateNotes(noteId, note);

      }
   }
}
