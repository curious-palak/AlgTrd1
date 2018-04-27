package com.fundoonotes.userservice;

/**
 * Purpose: This is UserDao Interface,contains defined methods, and this layer
 * responsible for interacting with Database.
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */
public interface IUserDao
{
   User saveUser(User user);

   User fetchUser(User user);

   User getUserById(int id);

   User getUserByEmail(String email);

   User getUserByRandomId(String randomUUID);

   User activateStatus(User user);

   User fetchEmailByUUID(String randomUUId);

   User updatePassword(User user);
}
