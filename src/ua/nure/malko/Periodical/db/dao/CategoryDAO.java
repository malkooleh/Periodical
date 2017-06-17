package ua.nure.malko.Periodical.db.dao;

import ua.nure.malko.Periodical.db.entity.Category;
import ua.nure.malko.Periodical.exception.DBException;

import java.sql.SQLException;

public interface CategoryDAO extends GenericDao<Category> {

    Category findCategoryByName(String categoryName) throws DBException;
}
