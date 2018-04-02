package com.fundoonotes.userservice;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Purpose:This class contains implementation of userDao interface and contains
 * methods to perform database operations.
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */
@Repository
public class UserDaoImpl implements IUserDao
{

   @Autowired
   private SessionFactory sessionFactory;

   public Session session;

   @Override
   public User saveUser(User user)
   {
      session = sessionFactory.openSession();
      session.save(user);
      return user;
   }

   @Override
   public User getUserById(int userId)
   {
      return (User) sessionFactory.openSession().get(User.class, userId);
   }

   @Override
   public User getUserByEmail(String email)
   {
      session = sessionFactory.getCurrentSession();

      Criteria criteria = session.createCriteria(User.class);

      criteria.add(Restrictions.eq("email", email));
      User user2 = (User) criteria.uniqueResult();

      return user2;
   }

   @Override
   public User fetchUser(User user)
   {

      session = sessionFactory.openSession();
      Criteria criteria = session.createCriteria(User.class);
      Criterion emailFetch = Restrictions.eq("email", user.getEmail());

      Criterion passwordFetch = Restrictions.eq("password", user.getPassword());

      Criterion criterion = Restrictions.and(emailFetch, passwordFetch);
      criteria.add(criterion);

      User userFetch = (User) criteria.uniqueResult();

      return userFetch;
   }

   @Override
   public User getUserByRandomId(String randomUUId)
   {
      session = sessionFactory.getCurrentSession();
      Criteria criteria = session.createCriteria(User.class);
      criteria.add(Restrictions.eq("randomUUId", randomUUId));
      User user = (User) criteria.uniqueResult();
      return user;
   }

   @Override
   public User activateStatus(User user)
   {
      session = sessionFactory.getCurrentSession();
      session.update(user);
      return user;
   }

   @Override
   public User fetchEmailByUUID(String randomUUId)
   {
      session = sessionFactory.getCurrentSession();
      Criteria criteria = session.createCriteria(User.class);
      criteria.add(Restrictions.eq("randomUUId", randomUUId));
      User user = (User) criteria.uniqueResult();
      return user;
   }

   @Override
   public User updatePassword(User user)
   {
      session = sessionFactory.getCurrentSession();
      session.update(user);
      return user;

   }
}
