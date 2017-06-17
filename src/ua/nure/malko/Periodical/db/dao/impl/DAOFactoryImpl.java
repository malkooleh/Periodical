package ua.nure.malko.Periodical.db.dao.impl;

import java.sql.Connection;

import ua.nure.malko.Periodical.exception.DBException;
import ua.nure.malko.Periodical.db.dao.CategoryDAO;
import ua.nure.malko.Periodical.db.dao.DAOFactory;
import ua.nure.malko.Periodical.db.dao.PeriodicalDAO;
import ua.nure.malko.Periodical.db.dao.SubscriptionDAO;
import ua.nure.malko.Periodical.db.dao.UserDAO;

public class DAOFactoryImpl implements DAOFactory {

	private Connection con;
	
	public DAOFactoryImpl(Connection connection) throws DBException{
		this.con = connection;
	}
	
	private UserDAO userDAO;
	private PeriodicalDAO periodicalDAO;
	private SubscriptionDAO subscriptionDAO;
	private CategoryDAO categoryDAO;

	@Override
	public UserDAO getUserDAO() {
		if (userDAO == null) {
            userDAO = new UserDAOImpl(con);
        }
        return userDAO;
	}

	@Override
	public PeriodicalDAO getPeriodicalDAO() {
		if(periodicalDAO == null){
			periodicalDAO = new PeriodicalDAOImpl(con);
		}
		return periodicalDAO;
	}

	@Override
	public SubscriptionDAO getSubscriptionDAO() {
		if(subscriptionDAO == null){
			subscriptionDAO = new SubscriptionDAOImpl(con);
		}
		return subscriptionDAO;
	}

	@Override
	public CategoryDAO getCategoryDAO() {
		if(categoryDAO == null){
			categoryDAO = new CategoryDAOImpl(con);
		}
		return categoryDAO;
	}

}
