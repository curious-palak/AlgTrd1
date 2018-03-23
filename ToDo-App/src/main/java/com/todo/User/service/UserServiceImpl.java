package com.todo.User.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.User.dao.IUserDao;
import com.todo.User.model.User;
import com.todo.User.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Transactional
	@Override
	public void registerUser(User user) {

		userDao.saveUser(user);
	}
}