package ua.nure.malko.Periodical.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.malko.Periodical.db.dao.GenericDao;
import ua.nure.malko.Periodical.exception.DBException;

public abstract class GenericDAOImpl<T> implements GenericDao<T> {

	private Class<T> type;

	private Connection connection;

	public GenericDAOImpl(Connection con, Class<T> type) {
		this.connection = con;
		this.type = type;
	}

	private final String SQL_FIND_ALL = "SELECT * FROM " + type.getName().toLowerCase() + "s";
	private final String SQL_GET_BY_ID = SQL_FIND_ALL + "WHERE id=?";

	@Override
	public List<T> findAll() throws DBException {
		List<T> allList = new ArrayList<>();
		try (Statement st = connection.createStatement()) {
			ResultSet rs = st.executeQuery(SQL_FIND_ALL);
			while (rs.next()) {
				allList.add(extract(rs));
			}
		} catch (SQLException e) {
			System.err.println("getAll() exception");
			throw new DBException( e.getMessage() );
		}
		return allList;
	}

	@Override
	public T findById(long id) throws DBException {
		T t = null;
		try (PreparedStatement pst = connection.prepareStatement(SQL_GET_BY_ID)) {
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				t = extract(rs);
			}
		} catch (SQLException e) {
			System.err.println("getAll() exception");
			throw new DBException( e.getMessage() );
		}
		return t;
	}

}
