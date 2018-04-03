package com.fundoonotes.noteservice;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fundoonotes.userservice.User;

/**
 * <p>
 * This is a Rest Controller for Notes With
 * {@link RestController @RestController}, we have added all general purpose
 * methods here those method will accept a rest request in JSON and will return
 * a JSON response.
 * </p>
 * <p>
 * The methods are self explanatory we have used <b>{@code @RestController}</b>
 * annotation to point incoming requests to this class, and
 * <b>{@link ResponseBody @ResponseBody}</b> annotation to point incoming
 * requests to appropriate Methods. <b>{@link RequestBody @RequestBody}</b>
 * annotation is used to accept data with request in JSON and Spring
 * ResponseEntity is used to return JSON as response to incoming request.
 * </p>
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 *
 */
@RestController
public class NotesController
{

   @Autowired
   INotesService notesService;

   /**
    * <p>
    * This rest API for creating notes With
    * {@link RequestMapping @RequestMapping} to mapped rest address.
    * </p>
    * 
    * @param notes Object to be save
    * @param request
    * @return ResponseEntity with HTTP status and message.
    */
   @PostMapping(value = "notes")
   public ResponseEntity<String> notesAdd(@RequestBody Notes notes, HttpServletRequest request)
   {

      User user = (User) request.getSession().getAttribute("userId");
      notesService.createNote(notes, user);
      return new ResponseEntity<>("Added note successfully..", HttpStatus.OK);
   }

   /**
    * <p>
    * This rest API for deleting notes by ID
    * {@link RequestMapping @RequestMapping} to mapped rest address.
    * </p>
    * 
    * @param noteId
    * @return ResponseEntity with HTTP status and message.
    */
   @DeleteMapping(value = "deleteNotes/{noteId}")
   public ResponseEntity<String> notesDelete(@PathVariable("noteId") int noteId)
   {

      notesService.deleteNotes(noteId);
      return new ResponseEntity<String>("Note deleted..", HttpStatus.OK);
   }

   /**
    * This rest API is for Updating notes by note Id
    * {@link RequestMapping @RequestMapping} to mapped rest address.
    * 
    * @param noteId
    * @param notes
    * @return ResponseEntity with HTTP status and message.
    */
   @RequestMapping(value = "updateNotes/{noteId}", method = RequestMethod.PUT)
   public ResponseEntity<?> notesUpdate(@PathVariable("noteId") int noteId, @RequestBody Notes notes)
   {
      notesService.updateNotes(noteId, notes);
      return new ResponseEntity<>("Notes updated successfully.", HttpStatus.OK);

   }

   /**
    * This rest API is to get notes by noteId
    * {@link RequestMapping @RequestMapping} to mapped rest address.
    * 
    * @param noteId
    * @return ResponseEntity with HTTP status and message.
    */
   @RequestMapping(value = "getNotes/{noteId}", method = RequestMethod.GET)
   public ResponseEntity<Notes> getNotes(@PathVariable("noteId") int noteId)
   {

      Notes notes = notesService.getNotesById(noteId);
      return new ResponseEntity<Notes>(notes, HttpStatus.OK);
   }
}