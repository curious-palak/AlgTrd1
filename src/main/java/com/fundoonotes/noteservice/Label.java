package com.fundoonotes.noteservice;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fundoonotes.userservice.User;

@Entity
@Table(name = "ToDoLabels")
public class Label
{
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int labelId;

   @Column
   private String labelTitle;

   @ManyToOne(cascade = CascadeType.DETACH)
   @JoinColumn(name = "userId")
   private User user;

   @JsonIgnore
   @ManyToMany(mappedBy = "label")
   private Set<Note> note;

   public Label()
   {

   }

   public Label(int labelId)
   {
      this.labelId = labelId;
   }

   public int getLabelId()
   {
      return labelId;
   }

   public void setLabelId(int labelId)
   {
      this.labelId = labelId;
   }

   public String getLabelTitle()
   {
      return labelTitle;
   }

   public void setLabelTitle(String labelTitle)
   {
      this.labelTitle = labelTitle;
   }

   public User getUser()
   {
      return user;
   }

   public void setUser(User user)
   {
      this.user = user;
   }

   public Set<Note> getNote()
   {
      return note;
   }

   public void setNote(Set<Note> note)
   {
      this.note = note;
   }
}
