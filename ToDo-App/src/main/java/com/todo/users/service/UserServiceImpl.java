package com.todo.users.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.users.dao.IUserDao;
import com.todo.users.model.User;
import com.todo.users.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Transactional
	@Override
	public void registerUser(User user) {
		userDao.saveUser(user);
	}

	@Override
	public User loginUser(User user) {
		
		return userDao.fetchUser(user);
	}
}