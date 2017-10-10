package com.company.SpringRESTSample.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.SpringRESTSample.dao.UserDao;
import com.company.SpringRESTSample.model.User;

@Service
@Transactional
public class UserService {

	private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
	
	@Autowired
	private UserDao userDao;
	
	public User findById(int id)
	{
		return userDao.getUserById(id); 
	}

	
	public User findByName(String name)
	{
		return userDao.getUserByName(name);
	}

	public void saveUser(User user)
	{
		userDao.addNewUser(user);
	}

	public User updateUser(User newUser)
	{
		User oldUser = findByName(newUser.getName());
		if(oldUser == null)
		{
			LOGGER.warning("Cannot update user as no user found with name : "+newUser.getName());
			return null;
		}
		oldUser.setAge(newUser.getAge());
		oldUser.setName(newUser.getName());
		oldUser.setSalary(newUser.getSalary());
		
		userDao.updateUser(oldUser);
		
		return oldUser;
	}

	public boolean deleteUserById(int id)
	{
		User u = findById(id);
		if(u ==null)
		{
			LOGGER.warning("Cannot delete user as no user with id : "+id+" was found");
			return false;
		}
		userDao.deleteUser(u);
		return true;
	}

	public List<User> findAllUsers()
	{
		return userDao.getAllUsers();
	}

	public boolean isUserExist(User user)
	{
		return findByName(user.getName())!=null;
	}

}
