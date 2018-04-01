package com.fundoonotes.userservice;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session;

	@Override
	public User saveUser(User user) {

		session = sessionFactory.openSession();
		session.save(user);
		return user;
	}

	@Override
	public User getUserById(int userId) {
		return (User) sessionFactory.openSession().get(User.class, userId);
	}

	@Override
	public User getUserByEmail(String email) {
		session=sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		List<User> list = criteria.list();
		return list.get(0);
		
		/*System.out.println("In dao");
		return (User) sessionFactory.openSession().get(User.class,email);*/
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
