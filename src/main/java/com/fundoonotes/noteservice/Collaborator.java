package com.fundoonotes.noteservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fundoonotes.userservice.User;

@Entity
@Table(name = "ToDoCollaborator")
public class Collaborator
{
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int collaboratorId;

   //@OneToMany
   @ManyToOne
   @JoinColumn(name = "owner",nullable = false)
   private User owner;

   @ManyToOne
   @JoinColumn(name = "sharedUser",unique= true)
   private User sharedUser;

   @JsonIgnore
   @ManyToOne
   @JoinColumn(name="noteId",nullable=false)
   private Note note;

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
