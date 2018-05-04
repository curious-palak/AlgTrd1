package com.fundoonotes.noteservice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fundoonotes.userservice.User;

import java.sql.Blob;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "ToDoNotes")
public class Note
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int noteId;

   @Column
   private String title;

   @Column
   private String description;

   @Column
   private Date date;

   @Column
   private Boolean inTrash = false;

   @Column
   private Boolean isPin = false;

   @Column
   private Boolean isArchive = false;

   @Column
   private String color;

   @Column
   private Date reminder;

   @ManyToOne(cascade = CascadeType.DETACH)
   @JoinColumn(name = "userId")
   private User user;

   @JsonIgnore
   @ManyToMany
   @JoinTable(name = "Label", joinColumns = { @JoinColumn(name = "noteId") }, inverseJoinColumns = {
         @JoinColumn(name = "labelId") })
   private Set<Label> label;

   @JsonIgnore
   @OneToMany(mappedBy = "note")
   private Set<Collaborator> collaborators;

   @Lob
   @Column(columnDefinition="Blob", name="noteImage")
   private byte[] noteImage;

   public int getnoteId()
   {
      return noteId;
   }

   public void setnoteId(int noteId)
   {
      this.noteId = noteId;
   }

   public String getTitle()
   {
      return title;
   }

   public void setTitle(String title)
   {
      this.title = title;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public Date getDate()
   {
      return date;
   }

   public void setDate(Date date)
   {
      this.date = date;
   }

   public User getUser()
   {
      return user;
   }

   public void setUser(User user)
   {
      this.user = user;
   }

   public Boolean getInTrash()
   {
      return inTrash;
   }

   public void setInTrash(Boolean inTrash)
   {
      this.inTrash = inTrash;
   }

   public Boolean getIsPin()
   {
      return isPin;
   }

   public void setIsPin(Boolean isPin)
   {
      this.isPin = isPin;
   }

   public Boolean getIsArchive()
   {
      return isArchive;
   }

   public void setIsArchive(Boolean isArchive)
   {
      this.isArchive = isArchive;
   }

   public String getColor()
   {
      return color;
   }

   public void setColor(String color)
   {
      this.color = color;
   }

   public Date getReminder()
   {
      return reminder;
   }

   public void setReminder(Date reminder)
   {
      this.reminder = reminder;
   }

   public Set<Label> getLabel()
   {
      return label;
   }

   public void setLabel(Set<Label> label)
   {
      this.label = label;
   }

   public Set<Collaborator> getCollaborators()
   {
      return collaborators;
   }

   public void setCollaborators(Set<Collaborator> collaborators)
   {
      this.collaborators = collaborators;
   }

   public byte[] getNoteImage()
   {
      return noteImage;
   }

   public void setNoteImage(byte[] noteImage)
   {
      this.noteImage = noteImage;
   }

}
