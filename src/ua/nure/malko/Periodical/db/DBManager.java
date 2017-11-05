package ua.nure.malko.Periodical.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import ua.nure.malko.Periodical.db.dao.DAOFactory;
import ua.nure.malko.Periodical.db.dao.impl.DAOFactoryImpl;
import ua.nure.malko.Periodical.exception.DBException;
import ua.nure.malko.Periodical.exception.Messages;

public class DBManager {

    //private static final Logger LOG = Logger.getLogger( DBManager.class );

    private static DBManager instance = null;

    private DataSource dataSource;
    private Connection connection;

    private DBManager() {
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch (ClassNotFoundException ex) {
            //LOG.error( Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex );
        }
//        try {
//            Context initContext = new InitialContext();
//            dataSource = (DataSource) initContext.lookup( "java:comp/env/jdbc/periodical" );
//            LOG.trace( "Data source ==> " + dataSource );
//        } catch (NamingException ex) {
//            LOG.error( "ERR_CANNOT_OBTAIN_DATA_SOURCE", ex );
//            //throw new DBException("ERR_CANNOT_OBTAIN_DATA_SOURCE", ex);
//        }
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private Connection getConnection() throws DBException {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException ex) {
            //LOG.error( Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex );
            throw new DBException( Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex );
        }
        return connection;
    }

    public void beginTransaction() throws DBException {
        try {
            getConnectionWithDriverManager().setAutoCommit( false );
        } catch (SQLException e) {
            throw new DBException( "Can't begin transaction", e );
        }
    }

    public void commitTransaction() throws DBException {
        try {
            getConnectionWithDriverManager().commit();
        } catch (SQLException e) {
            throw new DBException( "Can't commit transaction", e );
        }
    }

    public void rollbackTransaction() throws DBException {
        try {
            getConnectionWithDriverManager().rollback();
        } catch (SQLException e) {
            throw new DBException( "Can't rollback transaction", e );
        }
    }

    public void close() throws DBException {
        if (getConnectionWithDriverManager() != null) {
            try {
                getConnection().close();
            } catch (SQLException e) {
                throw new DBException( "Can't close connection", e );
            }
        }
    }

    public DAOFactory getDAOFactory() throws DBException {
        return new DAOFactoryImpl( getConnectionWithDriverManager() );
    }

    private Connection getConnectionWithDriverManager() throws DBException {
        try {
            connection = DriverManager
                    .getConnection( "jdbc:mysql://localhost:3306/periodical", "root", "root" );
        } catch (SQLException e) {
            throw new DBException( Messages.ERR_CANNOT_OBTAIN_CONNECTION, e );
        }
        return connection;
    }

}
