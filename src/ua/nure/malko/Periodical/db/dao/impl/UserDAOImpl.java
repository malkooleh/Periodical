package ua.nure.malko.Periodical.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.malko.Periodical.db.Fields;
import ua.nure.malko.Periodical.db.dao.UserDAO;
import ua.nure.malko.Periodical.db.entity.User;
import ua.nure.malko.Periodical.exception.DBException;

public class UserDAOImpl implements UserDAO {

    private Connection con;

    public UserDAOImpl(Connection con) {
        this.con = con;
    }

    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
    private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String SQL_UPDATE_USER = "UPDATE users SET password=?, first_name=?, last_name=?"
            + "	WHERE id=?";
    private static final String SQL_INSERT_USER = "INSERT INTO users "
            + "(login, password, first_Name, last_Name, role_Id) VALUES "
            + "(?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE id=?";

    @Override
    public List<User> findAll() throws DBException {
        List<User> userList = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery( SQL_FIND_ALL_USERS );
            while (rs.next()) {
                userList.add( extract( rs ) );
            }
        } catch (SQLException e) {
            System.err.println( "Error List<User> findAll()" );
            throw new DBException( e.getMessage() );
        }
        return userList;
    }

    @Override
    public User findById(long id) throws DBException {
        User user = new User();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement( SQL_FIND_USER_BY_ID );
            pst.setLong( 1, id );
            rs = pst.executeQuery();
            if (rs.next()) {
                user = extract( rs );
            }
        } catch (SQLException e) {
            System.err.println( "Error User findById(long id)" );
            throw new DBException( e.getMessage() );
        }
        return user;
    }

    @Override
    public User findByLogin(String login) throws DBException {
        User user = new User();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement( SQL_FIND_USER_BY_LOGIN );
            pst.setString( 1, login );
            rs = pst.executeQuery();
            if (rs.next()) {
                user = extract( rs );
            }
        } catch (SQLException e) {
            System.err.println( "Error User findByLogin(String login)" );
            throw new DBException( e.getMessage() );
        }
        return user;
    }

    @Override
    public void update(User entity) throws DBException {
        try (PreparedStatement pst = con.prepareStatement( SQL_UPDATE_USER );) {
            pst.setString( 1, entity.getPassword() );
            pst.setString( 2, entity.getFirstName() );
            pst.setString( 3, entity.getLastName() );
            pst.setLong( 4, entity.getId() );
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println( "Error User findByLogin(String login)" );
            throw new DBException( e.getMessage() );
        }
    }

    @Override
    public void insert(User entity) throws DBException {
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement( SQL_INSERT_USER );
            pst.setString( 1, entity.getLogin() );
            pst.setString( 2, entity.getPassword() );
            pst.setString( 3, entity.getFirstName() );
            pst.setString( 4, entity.getLastName() );
            pst.setInt( 5, entity.getRoleId() );
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println( "Error User findByLogin(String login)" );
            throw new DBException( e.getMessage() );
        }
    }

    @Override
    public void delete(User entity) throws DBException {
        try (PreparedStatement pst = con.prepareStatement( SQL_DELETE_USER );) {
            pst.setLong( 1, entity.getId() );
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println( "Error User findByLogin(String login)" );
            throw new DBException( e.getMessage() );
        }
    }

    @Override
    public User extract(ResultSet rs) throws DBException {
        User user = new User();
        try {
            user.setId( rs.getLong( Fields.ENTITY_ID ) );
            user.setLogin( rs.getString( Fields.USER_LOGIN ) );
            user.setPassword( rs.getString( Fields.USER_PASSWORD ) );
            user.setFirstName( rs.getString( Fields.USER_FIRST_NAME ) );
            user.setLastName( rs.getString( Fields.USER_LAST_NAME ) );
            user.setRoleId( rs.getInt( Fields.USER_ROLE_ID ) );
            user.setAccount( rs.getInt( Fields.USER_ACCOUNT ) );
        } catch (SQLException e) {
            throw new DBException( e.getMessage() );
        }
        return user;
    }

}
