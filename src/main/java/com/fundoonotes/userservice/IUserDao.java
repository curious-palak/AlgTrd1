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
   /** For saving users data
    * @param user
    * @return
    */
   User saveUser(User user);

   /** For fetching user
    * @param user
    * @return
    */
   User fetchUser(User user);

   /** For fetching user by Id
    * @param id
    * @return
    */
   User getUserById(int id);

   /** For fetching user By Email
    * @param email
    * @return
    */
   User getUserByEmail(String email);

   /** For fetching user by RandomId
    * @param randomUUID
    * @return
    */
   User getUserByRandomId(String randomUUID);

   /** For activating user
    * @param user
    * @return
    */
   User activateStatus(User user);

   /** For fetching email by randomUUId
    * @param randomUUId
    * @return
    */
   User fetchEmailByUUID(String randomUUId);

   /** For updating User's record
    * @param user
    * @return
    */
   User updateRecord(User user);
}
