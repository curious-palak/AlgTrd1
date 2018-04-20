package com.fundoonotes.noteservice;

import java.util.List;

/**
 * Purpose: This is NotesService Interface,contains defined methods, This layer
 * interact with controller.
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */
public interface INotesService
{
   
   /**For creating notes
    * @param notes
    * @param userId
    */
   void createNote(Notes notes, int userId);
   
   /**For fetching the notes
    * @param id
    * @return
    */
   List<Notes> getNotes(int id);

   /**For updating the notes
    * @param noteId
    * @param notes
    * @param userId
    */
   void updateNotes(int noteId, Notes notes, int userId);

   /**For deleting the notes
    * @param note
    * @param id
    * @return
    */
   boolean deleteNotes(Notes note, int id);

   /**For creating notes labels
    * @param label
    * @param userId
    */
   void createLabel(Label label,int userId);

   /**For getting notes label
    * @param userId
    * @return
    */
   List<Label> getLabel(int userId);
}
