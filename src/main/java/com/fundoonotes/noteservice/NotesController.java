package com.fundoonotes.noteservice;

import java.util.List;

import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fundoonotes.exception.CustomResponse;
import com.fundoonotes.exception.EmptyToken;
import com.fundoonotes.utility.JwtTokenUtility;

/**
 * ii
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
   @PutMapping(value = "createnotes")
   public ResponseEntity<CustomResponse> notesAdd(@RequestBody Note notes, HttpServletRequest request)
   {
      int userId = JwtTokenUtility.verifyToken(request.getHeader("Authorization"));
      logger.info("token->>" + request.getHeader("Authorization"));

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

   @PostMapping(value = "deletenotes")
   public ResponseEntity<CustomResponse> notesDelete(@RequestBody Note note, HttpServletRequest request)
   {
      int id = JwtTokenUtility.verifyToken(request.getHeader("Authorization"));

      notesService.deleteNotes(note, id);
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
   @RequestMapping(value = "updatenotes", method = RequestMethod.PUT)
   public ResponseEntity<?> notesUpdate(@RequestBody Note notes, HttpServletRequest request)
   {
      int userId = JwtTokenUtility.verifyToken(request.getHeader("Authorization"));
      int noteId = notes.getnoteId();

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
   @RequestMapping(value = "getnotes", method = RequestMethod.GET)
   public ResponseEntity<List<Note>> getNotes(HttpServletRequest request)
   {
      int id = JwtTokenUtility.verifyToken(request.getHeader("Authorization"));
      System.out.println("token->>" + request.getHeader("Authorization"));

      List<Note> notes = (List<Note>) notesService.getNotes(id);

      if (notes.size() != 0) {

         return new ResponseEntity<List<Note>>(notes, HttpStatus.OK);
      } else {
         response.setMessage("No content  available of notes..");
         response.setStatusCode(204);
         return new ResponseEntity<List<Note>>(notes, HttpStatus.NO_CONTENT);
      }
   }

   /* APIs for Label Controller.. */
   @PutMapping(value = "createlabel")
   public ResponseEntity<CustomResponse> createLabel(@RequestBody Label label, HttpServletRequest request)
   {

      System.out.println("In label Controller..");
      int userId = JwtTokenUtility.verifyToken(request.getHeader("Authorization"));

      if (request.getHeader("Authorization").isEmpty()) {
         throw new EmptyToken();
      }

      notesService.createLabel(label,userId);
      response.setMessage("LAbel Created Successfully..");
      response.setStatusCode(100);
      return new ResponseEntity<CustomResponse>(response, HttpStatus.ACCEPTED);
   }
   
   @RequestMapping(value = "getlabels", method = RequestMethod.GET)
   public ResponseEntity<List<Label>> getLabels(HttpServletRequest request)
   {
      int userId = JwtTokenUtility.verifyToken(request.getHeader("Authorization"));
      System.out.println("token->>" + request.getHeader("Authorization"));

      List<Label> labels = notesService.getLabel(userId);

      if (labels.size() != 0) {
         for (int i = 0; i < labels.size(); i++) {
            System.out.println(labels.get(i).getLabelTitle());
         }
            
         return new ResponseEntity<List<Label>>(labels, HttpStatus.OK);
      } else {
         response.setMessage("No labels available..");
         response.setStatusCode(204);
         return new ResponseEntity<List<Label>>(labels, HttpStatus.NO_CONTENT);
      }
   }
   
   @PostMapping(value="deletelabel")
   public ResponseEntity<CustomResponse> deleteLabel(@RequestBody Label label, HttpServletRequest request){
      
      int id= JwtTokenUtility.verifyToken(request.getHeader("Authorization"));
      System.out.println("In delete controllerr.." + id);
      
      notesService.deleteLabel(label,id);
      response.setMessage("Label deleted successfully..");
      response.setStatusCode(100);
      return new ResponseEntity<CustomResponse>(response,HttpStatus.OK);  
   }
   
   @RequestMapping(value = "updatelabel", method = RequestMethod.PUT)
   public ResponseEntity<?> updateLabel(@RequestBody Label label, HttpServletRequest request)
   {
      int userId = JwtTokenUtility.verifyToken(request.getHeader("Authorization"));
      int labelId = label.getLabelId();

      notesService.updateLabel(labelId, label, userId);
      response.setMessage("Notes updated successfully.");
      response.setStatusCode(200);
      return new ResponseEntity<>(response, HttpStatus.OK);
   }
   
   @PutMapping(value="/addremovelabel/{noteId}/{labelId}/{status}")
   public ResponseEntity<CustomResponse> addRemoveLabel(@PathVariable("noteId") int noteId,
                                                         @PathVariable("labelId") int labelId,
                                                           @PathVariable("status") boolean status){
      
      if(status) {
         notesService.addLabelOnNote(noteId,labelId);
         response.setMessage("Added label on notes..");
         response.setStatusCode(100);
         return new ResponseEntity<CustomResponse>(response,HttpStatus.OK);
      }
      else if(!status) {
         //deleting label
         notesService.deleteLabelOnNote(noteId,labelId);
         response.setMessage("Removed label on notes..");
         response.setStatusCode(100);
         return new ResponseEntity<CustomResponse>(response,HttpStatus.OK);
      }
      return null;
   }
   
    /**Collaborator Api's*/
   
   @PutMapping(value = "addCollaborator")
   public ResponseEntity<CustomResponse> createCollaborator(@RequestBody Collaborator collaborator, HttpServletRequest request) {

     System.out.println("Checkkking->"+collaborator.getCollaboratorId()+" "+collaborator.getOwner()+collaborator.getNote());
       

      int userId = JwtTokenUtility.verifyToken(request.getHeader("Authorization"));
               logger.info("token->>" + request.getHeader("Authorization"));
         
      if (request.getHeader("Authorization").isEmpty()) {
                throw new EmptyToken();
             }
      
      notesService.createCollaborator(collaborator, userId);
      response.setMessage("Collaborator added successfully..");
      response.setStatusCode(200);
      return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
   }
}