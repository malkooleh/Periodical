package ua.nure.malko.Periodical.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.malko.Periodical.db.Fields;
import ua.nure.malko.Periodical.db.dao.PeriodicalDAO;
import ua.nure.malko.Periodical.db.entity.Periodical;
import ua.nure.malko.Periodical.exception.DBException;

class PeriodicalDAOImpl implements PeriodicalDAO {

    private Connection con;

    PeriodicalDAOImpl(Connection con) {
        this.con = con;
    }

    private static final String SQL_FIND_ALL_PERIODICALS = "SELECT * FROM periodicals";
    private static final String SQL_FIND_PERIODICAL_BY_ID = "SELECT * FROM periodicals WHERE id=?";
    private static final String SQL_UPDATE_PERIODICAL = "UPDATE periodicals SET name=?, price=?, category_id=?"
            + "	WHERE id=?";
    private static final String SQL_FIND_PERIODICALS_BY_NAME = "SELECT * FROM periodicals WHERE name=?";
    private static final String SQL_FIND_PERIODICALS_BY_CATEGORY_ID = "SELECT * FROM periodicals WHERE category_id=?";
    private static final String SQL_INSERT_PERIODICAL = "INSERT INTO periodicals " + "(name, price, category_id) VALUES "
            + "(?, ?, ?)";
    private static final String SQL_DELETE_PERIODICAL = "DELETE FROM periodicals WHERE id=?";
    private static final String SQL_FIND_PERIODICALS_BY_SEARCHSTRING = "SELECT * FROM periodicals p WHERE LOWER(p.name)";

    @Override
    public List<Periodical> findAll() throws DBException {
        List<Periodical> periodicalList = new ArrayList<>();
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery( SQL_FIND_ALL_PERIODICALS )) {
            while (rs.next()) {
                periodicalList.add( extract( rs ) );
            }
        } catch (SQLException e) {
            System.err.println( "Error List<Periodical> findAll()" );
            throw new DBException( e.getMessage() );
        }
        return periodicalList;
    }

    @Override
    public Periodical findById(long id) throws DBException {
        Periodical periodical = new Periodical();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement( SQL_FIND_PERIODICAL_BY_ID );
            pst.setLong( 1, id );
            rs = pst.executeQuery();
            if (rs.next()) {
                periodical = extract( rs );
            }
        } catch (SQLException e) {
            System.err.println( "Error Periodical findById(long id)" );
            throw new DBException( e.getMessage() );
        }
        return periodical;
    }

    @Override
    public void update(Periodical entity) throws DBException {
        try (PreparedStatement pst = con.prepareStatement( SQL_UPDATE_PERIODICAL );) {
            pst.setString( 1, entity.getName() );
            pst.setInt( 2, entity.getPrice() );
            pst.setLong( 3, entity.getCategoryId() );
            pst.setLong( 4, entity.getId() );
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println( "Error update(Periodical entity)" );
            throw new DBException( e.getMessage() );
        }
    }

    @Override
    public void insert(Periodical entity) throws DBException {
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement( SQL_INSERT_PERIODICAL );
            pst.setString( 1, entity.getName() );
            pst.setInt( 2, entity.getPrice() );
            pst.setLong( 3, entity.getCategoryId() );
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println( "Error insert(Periodical entity)" );
            throw new DBException( e.getMessage() );
        }
    }

    @Override
    public void delete(Periodical entity) throws DBException {
        try (PreparedStatement pst = con.prepareStatement( SQL_DELETE_PERIODICAL );) {
            pst.setLong( 1, entity.getId() );
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println( "Error delete(Periodical entity)" );
            throw new DBException( e.getMessage() );
        }
    }

    @Override
    public Periodical extract(ResultSet rs) throws DBException {
        Periodical periodical = new Periodical();
        try {
            periodical.setId( rs.getLong( Fields.ENTITY_ID ) );
            periodical.setName( rs.getString( Fields.PERIODICAL_NAME ) );
            periodical.setPrice( rs.getInt( Fields.PERIODICAL_PRICE ) );
            periodical.setCategoryId( rs.getLong( Fields.PERIODICAL_CATEGORY_ID ) );
            periodical.setImage( rs.getBytes( Fields.PERIODICAL_IMAGE ) );
        } catch (SQLException e) {
            throw new DBException( e.getMessage() );
        }
        return periodical;
    }

    @Override
    public Periodical findByName(String name) throws DBException {
        Periodical periodical = new Periodical();
        ResultSet rs = null;
        try (PreparedStatement pst = con.prepareStatement( SQL_FIND_PERIODICALS_BY_NAME )) {
            pst.setString( 1, name );
            rs = pst.executeQuery();
            if (rs.next()) {
                periodical = extract( rs );
            }
        } catch (SQLException e) {
            System.err.println( "Error findByName(String name) " );
            throw new DBException( e.getMessage() );
        }
        return periodical;
    }

    @Override
    public List<Periodical> findByCategoryID(long categoryId) throws DBException {
        List<Periodical> periodicalList = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement pst = con.prepareStatement( SQL_FIND_PERIODICALS_BY_CATEGORY_ID )) {
            pst.setLong( 1, categoryId );
            rs = pst.executeQuery();
            while (rs.next()) {
                periodicalList.add( extract( rs ) );
            }
        } catch (SQLException e) {
            System.err.println( "Error List<Periodical> findByCategoryID()" );
            throw new DBException( e.getMessage() );
        }
        return periodicalList;
    }

    @Override
    public List<Periodical> findBySearch(String searchStr) throws DBException {
        String search = "like '%" + searchStr.toLowerCase() + "%' order by p.name ";
        List<Periodical> periodicalList = new ArrayList<>();
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery( SQL_FIND_PERIODICALS_BY_SEARCHSTRING + search )) {
            while (rs.next()) {
                periodicalList.add( extract( rs ) );
            }
        } catch (SQLException e) {
            System.err.println( "Error findBySearch(String searchStr) " );
            throw new DBException( e.getMessage() );
        }
        return periodicalList;
    }

}
