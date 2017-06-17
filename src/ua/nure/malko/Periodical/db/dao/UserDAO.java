package ua.nure.malko.Periodical.db.dao;

import ua.nure.malko.Periodical.db.entity.User;
import ua.nure.malko.Periodical.exception.DBException;

public interface UserDAO extends GenericDao<User> {

    User findByLogin(String login) throws DBException;

}
