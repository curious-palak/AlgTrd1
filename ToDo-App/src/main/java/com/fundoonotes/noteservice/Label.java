package com.fundoonotes.noteservice;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fundoonotes.userservice.User;

@Entity
@Table(name="ToDoLabels")
public class Label
{
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   private int labelId;
   
   @Column
   private String labelTitle;
   
   @ManyToOne(cascade=CascadeType.DETACH)
   @JoinColumn(name="userId")
   private User user;
   
   /*@ManyToMany(mappedBy = "label")
   private Set<Notes> note;*/

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
}
