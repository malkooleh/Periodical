package ua.nure.malko.Periodical.db.dao;

import ua.nure.malko.Periodical.db.entity.Periodical;
import ua.nure.malko.Periodical.exception.DBException;

import java.sql.SQLException;
import java.util.List;

public interface PeriodicalDAO extends GenericDao<Periodical> {

    Periodical findByName(String name) throws DBException;

    List<Periodical> findByCategoryID(long categoryId) throws DBException;

    List<Periodical> findBySearch(String searchStr) throws DBException;
}
