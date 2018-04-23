package com.fundoonotes.noteservice;

import java.util.List;

import com.fundoonotes.userservice.User;

/**
 * Purpose: This is NotesDao Interface,contains defined methods, and this layer
 * responsible for interacting with Database.
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */
public interface INotesDao {

	/**For creating user
	 * @param notes
	 * @return
	 */
	boolean createNotes(Notes notes);
	
	//boolean deleteNotes(Notes noteId);
	
	/**For updating notes
	 * @param noteId
	 * @param notes
	 * @return
	 */
	boolean updateNotes(int noteId,Notes notes);

	//Notes getNotesById(int noteId);

	/**For fetching notes
	 * @param user
	 * @return
	 */
	List<Notes> getNotes(User user);

   /**
    * @param id
    * @return
    */
   Notes getNote(int id);

   /**for fetching notes by Id
    * @param noteId
    * @return
    */
   Notes getNoteById(int noteId);

   /**For deleting notes by Id
    * @param noteId
    * @return
    */
   boolean deleteNotes(int noteId);

   /**For creating Label
    * @param label
    */
   void createLabel(Label label);

   /**For fetching Label
    * @param user
    * @return
    */
   List<Label> getLabel(User user);

   /**For deleting label
    * @param labelId
    * @return
    */
   boolean deleteLabel(int labelId);

   void updateLabel(int labelId, Label label);
   
   //List<Notes> getNotesById(User user);
}
