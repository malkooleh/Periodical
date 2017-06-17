package ua.nure.malko.Periodical.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.malko.Periodical.db.Fields;
import ua.nure.malko.Periodical.db.bean.UserSubscriptionBean;
import ua.nure.malko.Periodical.db.dao.SubscriptionDAO;
import ua.nure.malko.Periodical.db.entity.Subscription;
import ua.nure.malko.Periodical.exception.DBException;

public class SubscriptionDAOImpl implements SubscriptionDAO {

    private Connection con;

    public SubscriptionDAOImpl(Connection con) {
        this.con = con;
    }

    private static final String SQL_FIND_ALL_SUBSCRIPTIONS = "SELECT * FROM subscriptions";
    private static final String SQL_FIND_SUBSCRIPTION_BY_ID = "SELECT * FROM subscriptions WHERE id=?";
    private static final String SQL_UPDATE_SUBSCRIPTION = "UPDATE subscriptions SET bill=?, status_id=?, user_id=?"
            + "	WHERE id=?";
    private static final String SQL_FIND_SUBSCRIPTIONS_BY_STATUS_AND_USER = "SELECT * FROM subscriptions WHERE status_id=? AND user_id=?";
    private static final String SQL_INSERT_SUBSCRIPTION = "INSERT INTO subscriptions "
            + "(bill, status_id, user_id) values "
            + "(?, ?, ?)";
    private static final String SQL_DELETE_SUBSCRIPTION = "DELETE FROM subscriptions WHERE id=?";
    private static final String SQL_GET_USER_SUBSCRIPTION_BEANS = "SELECT o.id, u.first_name, u.last_name, o.bill, s.name"
            + "	FROM users u, subscriptions o, statuses s"
            + "	WHERE o.user_id=u.id AND o.status_id=s.id";

    @Override
    public List<Subscription> findAll() throws DBException {
        List<Subscription> userSubscriptionBeanList = new ArrayList<>();
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery( SQL_FIND_ALL_SUBSCRIPTIONS )) {
            while (rs.next()) {
                userSubscriptionBeanList.add( extract( rs ) );
            }
        } catch (SQLException e) {
            System.err.println( "Error List<Subscription> findAll()" );
            throw new DBException( e.getMessage() );
        }
        return userSubscriptionBeanList;
    }

    @Override
    public Subscription findById(long id) throws DBException {
        Subscription sub = new Subscription();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement( SQL_FIND_SUBSCRIPTION_BY_ID );
            pst.setLong( 1, id );
            rs = pst.executeQuery();
            if (rs.next()) {
                sub = extract( rs );
            }
        } catch (SQLException e) {
            System.err.println( "Error Subscription findById(long id)" );
            throw new DBException( e.getMessage() );
        }
        return sub;
    }

    @Override
    public void update(Subscription entity) throws DBException {
        try (PreparedStatement pst = con.prepareStatement( SQL_UPDATE_SUBSCRIPTION );) {
            pst.setInt( 1, entity.getBill() );
            pst.setInt( 2, entity.getStatusId() );
            pst.setLong( 3, entity.getUserId() );
            pst.setLong( 4, entity.getId() );
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println( "Error update(Subscription entity)" );
            throw new DBException( e.getMessage() );
        }
    }

    @Override
    public void insert(Subscription entity) throws DBException {
        try (PreparedStatement pst = con.prepareStatement( SQL_INSERT_SUBSCRIPTION );) {
            pst.setInt( 1, entity.getBill() );
            pst.setInt( 2, entity.getStatusId() );
            pst.setLong( 3, entity.getUserId() );
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println( "Error insert(Subscription entity)" );
            throw new DBException( e.getMessage() );
        }
    }

    @Override
    public void delete(Subscription entity) throws DBException {
        try (PreparedStatement pst = con.prepareStatement( SQL_DELETE_SUBSCRIPTION );) {
            pst.setLong( 1, entity.getId() );
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println( "Error delete(Subscription entity)" );
            throw new DBException( e.getMessage() );
        }
    }

    @Override
    public Subscription extract(ResultSet rs) throws DBException {
        Subscription sub = new Subscription();
        try {
            sub.setId( rs.getLong( Fields.ENTITY_ID ) );
            sub.setBill( rs.getInt( Fields.SUBSCRIPTION_BILL ) );
            sub.setStatusId( rs.getInt( Fields.SUBSCRIPTION_STATUS_ID ) );
            sub.setUserId( rs.getLong( Fields.SUBSCRIPTION_USER_ID ) );
        } catch (SQLException e) {
            throw new DBException( e.getMessage() );
        }
        return sub;
    }

    @Override
    public Subscription findByStatusAndUser(long userId, int statusId) throws DBException {
        Subscription sub = new Subscription();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement( SQL_FIND_SUBSCRIPTIONS_BY_STATUS_AND_USER );
            pst.setInt( 1, statusId );
            pst.setLong( 2, userId );
            rs = pst.executeQuery();
            if (rs.next()) {
                sub = extract( rs );
            }
        } catch (SQLException e) {
            System.err.println( "Error Subscription findById(long id)" );
            throw new DBException( e.getMessage() );
        }
        return sub;
    }

    public List<UserSubscriptionBean> getUserSubscriptionBeans() throws DBException {
        List<UserSubscriptionBean> userSubscriptionBean = new ArrayList<>();
        ResultSet rs = null;
        try(Statement stmt = con.createStatement();) {
            rs = stmt.executeQuery(SQL_GET_USER_SUBSCRIPTION_BEANS);
            while (rs.next()) {
                userSubscriptionBean.add(extractUserSubscriptionBean(rs));
            }
        } catch (SQLException ex) {
            throw new DBException("ERR_CANNOT_OBTAIN_USER_SUBSCRIPTION_BEANS", ex);
        }
        return userSubscriptionBean;
    }

    private UserSubscriptionBean extractUserSubscriptionBean(ResultSet rs) throws SQLException {
        UserSubscriptionBean bean = new UserSubscriptionBean();
        bean.setId(rs.getLong(Fields.USER_SUBSCRIPTION_BEAN_SUBSCRIPTION_ID));
        bean.setSubscriptionBill(rs.getInt(Fields.USER_SUBSCRIPTION_BEAN_SUBSCRIPTION_BILL));
        bean.setUserFirstName(rs.getString(Fields.USER_SUBSCRIPTION_BEAN_USER_FIRST_NAME));
        bean.setUserLastName(rs
                .getString(Fields.USER_SUBSCRIPTION_BEAN_USER_LAST_NAME));
        bean.setStatusName(rs.getString(Fields.USER_SUBSCRIPTION_BEAN_STATUS_NAME));
        return bean;
    }

}
