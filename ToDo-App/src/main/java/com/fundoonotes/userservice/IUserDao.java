package com.fundoonotes.userservice;

public interface IUserDao {

	public User saveUser(User user);

	public User fetchUser(User user);

	public User getUserById(int userId);
	
	public User getUserByEmail(String email);

   public User getUserByRandomId(String randomUUID);

   public User activateStatus(User user);
}
