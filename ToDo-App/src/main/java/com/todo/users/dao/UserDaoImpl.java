package com.todo.users.dao;

import org.hibernate.Criteria;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.todo.users.model.User;

@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session;

	@Override
	public void saveUser(User user) {

		session = sessionFactory.openSession();
		session.save(user);
	}

	@Override
	public User fetchUser(User user) {

		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		Criterion emailFetch = Restrictions.eq("email", user.getEmail());
		System.out.println(emailFetch);
		Criterion passwordFetch = Restrictions.eq("password", user.getPassword());
		System.out.println(passwordFetch);

		Criterion criterion = Restrictions.and(emailFetch, passwordFetch);
		criteria.add(criterion);

		User userFetch = (User) criteria.uniqueResult();
		System.out.println(userFetch.getEmail() + " " + userFetch.getPassword());
		return userFetch;
	}
}
