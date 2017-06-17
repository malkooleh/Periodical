package ua.nure.malko.Periodical.db.dao;

import ua.nure.malko.Periodical.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface GenericDao <T> {
	
	List<T> findAll() throws DBException;

    T findById(long id) throws DBException;
    
    void update(T entity) throws DBException;

    void insert(T entity) throws DBException;

    void delete(T entity) throws DBException;
    
    T extract(ResultSet rs) throws DBException;

}
