package ua.nure.malko.Periodical.db.dao;

import ua.nure.malko.Periodical.db.bean.UserSubscriptionBean;
import ua.nure.malko.Periodical.db.entity.Subscription;
import ua.nure.malko.Periodical.exception.DBException;

import java.util.List;

public interface SubscriptionDAO extends GenericDao<Subscription> {

    Subscription findByStatusAndUser(long userId, int statusId) throws DBException;

    List<UserSubscriptionBean> getUserSubscriptionBeans() throws DBException;

}
