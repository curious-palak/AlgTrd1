package com.fundoonotes.noteservice;

import com.fundoonotes.userservice.User;

public class CollaboratorDtoRes
{
   private int collaboratorId;
   
   private User owner;
   
   private User sharedUser;
   
   private Note note;

   public CollaboratorDtoRes(Collaborator collObj)
   {
      this.owner=collObj.getOwner();
      this.sharedUser=collObj.getSharedUser();
      this.note=collObj.getNote();
   }

   public int getCollaboratorId()
   {
      return collaboratorId;
   }

   public void setCollaboratorId(int collaboratorId)
   {
      this.collaboratorId = collaboratorId;
   }

   public User getOwner()
   {
      return owner;
   }

   public void setOwner(User owner)
   {
      this.owner = owner;
   }

   public User getSharedUser()
   {
      return sharedUser;
   }

   public void setSharedUser(User sharedUser)
   {
      this.sharedUser = sharedUser;
   }

   public Note getNote()
   {
      return note;
   }

   public void setNote(Note note)
   {
      this.note = note;
   }
  
}
