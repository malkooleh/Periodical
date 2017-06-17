package ua.nure.malko.Periodical.db.dao.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ua.nure.malko.Periodical.db.DBManager;
import ua.nure.malko.Periodical.db.dao.UserDAO;
import ua.nure.malko.Periodical.db.entity.User;

public class UserDAOImplTest {

	DBManager db;

	@Test
	public void findAlltest() throws Exception {
		db = DBManager.getInstance();
		List<User> list;
		UserDAO us = db.getDAOFactory().getUserDAO();
		list = us.findAll();
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > 0);
	}

	@Test
	public void findByLoginTest() throws Exception {
		String login = "admin";
		db = DBManager.getInstance();
		User us = db.getDAOFactory().getUserDAO().findByLogin(login);
		System.out.println(us.toString());
		Assert.assertNotNull(us);
		Assert.assertTrue("admin".equals(us.getLogin()));
	}

	@Test
	public void findByIdTest() throws Exception {
		int id = 1;
		db = DBManager.getInstance();
		User us = db.getDAOFactory().getUserDAO().findById(id);
		System.out.println(us.toString());
		Assert.assertNotNull(us);
		Assert.assertTrue("Malko".equals(us.getLastName()));
	}

	@Test
	public void insertTest() throws Exception {
		db = DBManager.getInstance();
		User us = new User();
		us.setLogin("aaaa");
		us.setFirstName("Jon");
		us.setLastName("Jonson");
		us.setPassword("1212");
		us.setRoleId(1);

		db.getDAOFactory().getUserDAO().insert(us);
		
		User user = db.getDAOFactory().getUserDAO().findById(4);
		Assert.assertTrue(us.getFirstName().equals(user.getFirstName()));

	}

}
