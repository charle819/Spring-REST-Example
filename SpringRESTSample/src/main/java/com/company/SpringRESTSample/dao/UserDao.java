package com.company.SpringRESTSample.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.company.SpringRESTSample.model.User;

@Repository
public class UserDao extends RootDao<Integer, User> {

	private static final Logger LOGGER = Logger.getLogger(UserDao.class.getName());

	public User getUserById(int id) {
		User u = getById(id);
		if (u == null) {
			LOGGER.warning("NO USER FOUND WITH ID : " + id);
		}
		return u;
	}

	public User getUserByName(String name) {
		Criteria c = getCriteria();
		c.add(Restrictions.eq("name", name));
		User u = (User) c.uniqueResult();
		return u;
	}

	public void addNewUser(User u) {
		persist(u);
	}

	public void updateUser(User u) {
		update(u);
	}

	public void deleteUser(User u) {
		delete(u);
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {

		Criteria c = getCriteria();
		return (List<User>) c.list();
	}
}
