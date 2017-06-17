package ua.nure.malko.Periodical.db.dao.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.nure.malko.Periodical.db.DBManager;
import ua.nure.malko.Periodical.db.bean.UserSubscriptionBean;

import java.util.List;

import static org.junit.Assert.*;

public class SubscriptionDAOImplTest {

    DBManager dbManager = DBManager.getInstance();

    @Test
    public void findAll() throws Exception {

    }

    @Test
    public void findById() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void insert() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void extract() throws Exception {

    }

    @Test
    public void findByStatusAndUser() throws Exception {

    }

    @Test
    public void getUserSubscriptionBeans() throws Exception {
        List<UserSubscriptionBean> subscriptionBeen;
        subscriptionBeen = dbManager.getDAOFactory().getSubscriptionDAO().getUserSubscriptionBeans();
        Assert.assertNotNull(subscriptionBeen);
        Assert.assertTrue(subscriptionBeen.size() > 0);
    }

}