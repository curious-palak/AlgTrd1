package com.fundoonotes.userservice;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fundoonotes.noteservice.Notes;

@Entity
@Table(name = "ToDoRegistration")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="userId")
	private int userId;

	@Column
	private String name;

	@Column
	private String email;

	@Column
	private String password;

	@Column
	private String mobileNo;
	
	@Column
	private boolean status;
	
	@Column
	private String randomUUId;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private Set<Notes> notes;

	
   public User(UserDTO userdto)
   {
     
      this.name =userdto.getName();
      this.email = userdto.getEmail();
      this.password = userdto.getPassword();
      this.mobileNo = userdto.getMobileNo();
   }

   public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Set<Notes> getNotes() {
		return notes;
	}

	public void setNotes(Set<Notes> notes) {
		this.notes = notes;
	}

   public boolean isStatus()
   {
      return status;
   }

   public void setStatus(boolean status)
   {
      this.status = status;
   }

   public String getRandomUUId()
   {
      return randomUUId;
   }

   public void setRandomUUId(String randomUUId)
   {
      this.randomUUId = randomUUId;
   }
}
