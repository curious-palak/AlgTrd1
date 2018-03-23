package com.todo.User.dao;

import javax.management.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.todo.User.dao.IUserDao;
import com.todo.User.model.User;

@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	private SessionFactory sessionFactory;
	Session session;

	@Override
	public void saveUser(User user) {
		session = sessionFactory.openSession();
		session.save(user);
	}

	@Override
	public void loginUser(String name, String password) {

		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);

	}

	/*
	 * @Override public User get(int userId) { return (User)
	 * sessionFactory.openSession().get(User.class, userId); }
	 * 
	 * @Override public void deleteUser(int userId) { session =
	 * sessionFactory.openSession(); // Query query = (Query)
	 * session.createQuery("delete from User where id=:id "); User user = (User)
	 * session.byId(User.class).load(userId); session.delete(user); }
	 */

}
