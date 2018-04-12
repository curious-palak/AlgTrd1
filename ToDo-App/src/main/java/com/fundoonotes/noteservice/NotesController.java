package com.fundoonotes.noteservice;

import java.util.logging.Logger;

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

import com.fundoonotes.exception.CustomResponse;
import com.fundoonotes.exception.EmptyToken;
import com.fundoonotes.userservice.User;
import com.fundoonotes.userservice.UserController;
import com.fundoonotes.utility.JwtTokenUtility;

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

   CustomResponse response = new CustomResponse();

   private static Logger logger = Logger.getLogger(NotesController.class.getName());

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
   @PostMapping(value = "createnotes")
   public ResponseEntity<CustomResponse> notesAdd(@RequestBody Notes notes, HttpServletRequest request)
   {
      int userId = JwtTokenUtility.verifyToken(request.getHeader("Authorization"));
      System.out.println("token->>"+request.getHeader("Authorization"));
      
      if (request.getHeader("Authorization").isEmpty()) {
         throw new EmptyToken();
      }

      notesService.createNote(notes, userId);
      response.setMessage("Added note successfully..");
      response.setStatusCode(200);
      return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);

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
   @DeleteMapping(value = "deletenotes/{noteId}")
   public ResponseEntity<CustomResponse> notesDelete(@PathVariable("noteId") int noteId, HttpServletRequest request)
   {
      int userId = JwtTokenUtility.verifyToken(request.getHeader("Authorization"));
      notesService.deleteNotes(noteId, userId);
      response.setMessage("Note deleted..");
      response.setStatusCode(200);
      return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
   }

   /**
    * This rest API is for Updating notes by note Id
    * {@link RequestMapping @RequestMapping} to mapped rest address.
    * 
    * @param noteId
    * @param notes
    * @return ResponseEntity with HTTP status and message.
    */
   @RequestMapping(value = "updatenotes/{noteId}", method = RequestMethod.PUT)
   public ResponseEntity<?> notesUpdate(@PathVariable("noteId") int noteId, @RequestBody Notes notes,
         HttpServletRequest request)
   {
      int userId = JwtTokenUtility.verifyToken(request.getHeader("Authorization"));
      notesService.updateNotes(noteId, notes, userId);
      response.setMessage("Notes updated successfully.");
      response.setStatusCode(200);
      return new ResponseEntity<>(response, HttpStatus.OK);
   }

   /**
    * This rest API is to get notes by noteId
    * {@link RequestMapping @RequestMapping} to mapped rest address.
    * 
    * @param noteId
    * @return ResponseEntity with HTTP status and message.
    */
   @RequestMapping(value = "getnotes/{noteId}", method = RequestMethod.GET)
   public ResponseEntity<Notes> getNotes(@PathVariable("noteId") int noteId)
   {

      Notes notes = notesService.getNotesById(noteId);
      logger.info("Notes retrived successfully..");
      return new ResponseEntity<Notes>(notes, HttpStatus.OK);
   }
}