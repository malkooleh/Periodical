package ua.nure.malko.Periodical.db.dao;

public interface DAOFactory {
	
	UserDAO getUserDAO();
	
	PeriodicalDAO getPeriodicalDAO();
	
	SubscriptionDAO getSubscriptionDAO();
	
	CategoryDAO getCategoryDAO();

}
