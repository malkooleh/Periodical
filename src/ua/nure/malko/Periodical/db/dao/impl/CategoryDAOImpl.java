package ua.nure.malko.Periodical.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.malko.Periodical.db.Fields;
import ua.nure.malko.Periodical.db.dao.CategoryDAO;
import ua.nure.malko.Periodical.db.entity.Category;
import ua.nure.malko.Periodical.exception.DBException;

class CategoryDAOImpl implements CategoryDAO {

    private Connection con;

    CategoryDAOImpl(Connection connection) {
        this.con = connection;
    }

    private static final String SQL_FIND_ALL_CATEGORIES = "SELECT * FROM categories";
    private static final String SQL_FIND_CATEGORY_BY_NAME = "SELECT * FROM categories WHERE name=?";
    private static final String SQL_FIND_CATEGORY_BY_ID = "SELECT * FROM categories WHERE id=?";
    private static final String SQL_UPDATE_CATEGORY = "UPDATE categories SET name=?"
            + "	WHERE id=?";
    private static final String SQL_INSERT_CATEGORY = "INSERT INTO categories "
            + "(name) VALUES (?)";
    private static final String SQL_DELETE_CATEGORY = "DELETE FROM categories WHERE id=?";

    @Override
    public List<Category> findAll() throws DBException {
        List<Category> categoryList = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery( SQL_FIND_ALL_CATEGORIES );
            while (rs.next()) {
                categoryList.add( extract( rs ) );
            }
        } catch (SQLException e) {
            System.err.println( "Error List<Category> findAll()" );
            throw new DBException( e.getMessage() );
        }
        return categoryList;
    }

    @Override
    public Category findById(long id) throws DBException {
        Category category = new Category();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement( SQL_FIND_CATEGORY_BY_ID );
            pst.setLong( 1, id );
            rs = pst.executeQuery();
            if (rs.next()) {
                category = extract( rs );
            }
        } catch (SQLException e) {
            System.err.println( "Error Category findById(long id)" );
            throw new DBException( e.getMessage() );
        }
        return category;
    }

    @Override
    public void update(Category entity) throws DBException {
        try (PreparedStatement pst = con.prepareStatement( SQL_UPDATE_CATEGORY )) {
            pst.setString( 1, entity.getName() );
            pst.setLong( 2, entity.getId() );
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println( "Error update(Category entity)" );
            throw new DBException( e.getMessage() );
        }
    }

    @Override
    public void insert(Category entity) throws DBException {
        try (PreparedStatement pst = con.prepareStatement( SQL_INSERT_CATEGORY );) {
            pst.setString( 1, entity.getName() );
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println( "Error insert(Category entity)" );
            throw new DBException( e.getMessage() );
        }
    }

    @Override
    public void delete(Category entity) throws DBException {
        try (PreparedStatement pst = con.prepareStatement( SQL_DELETE_CATEGORY );) {
            pst.setLong( 1, entity.getId() );
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println( "Error delete(Category entity)" );
            throw new DBException( e.getMessage() );
        }
    }

    @Override
    public Category extract(ResultSet rs) throws DBException {
        Category category = new Category();
        try {
            category.setId( rs.getLong( Fields.ENTITY_ID ) );
            category.setName( rs.getString( Fields.CATEGORY_NAME ) );
        } catch (SQLException e) {
            throw new DBException( e.getMessage() );
        }
        return category;
    }

    @Override
    public Category findCategoryByName(String categoryName) throws DBException {
        Category category = new Category();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement( SQL_FIND_CATEGORY_BY_NAME );
            pst.setString( 1, categoryName );
            rs = pst.executeQuery();
            if (rs.next()) {
                category = extract( rs );
            }
        } catch (SQLException e) {
            System.err.println( "Error Category findCategoryByName(String categoryName)" );
            throw new DBException( e.getMessage() );
        }
        return category;
    }

}
