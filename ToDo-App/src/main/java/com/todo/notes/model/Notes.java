package com.todo.notes.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.todo.users.model.User;

@Entity
@Table(name = "ToDoNotes")
public class Notes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int noteId;
	
	@Column
	private String title;
	
	@Column
	private String description;
	
	@Column
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	public int getnoteId() {
		return noteId;
	}

	public void setnoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
