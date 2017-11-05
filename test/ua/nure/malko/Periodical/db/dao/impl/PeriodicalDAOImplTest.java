package ua.nure.malko.Periodical.db.dao.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.nure.malko.Periodical.db.DBManager;
import ua.nure.malko.Periodical.db.dao.PeriodicalDAO;
import ua.nure.malko.Periodical.db.entity.Periodical;

import java.util.List;

import static org.junit.Assert.*;

public class PeriodicalDAOImplTest {

    PeriodicalDAO periodicalDAO;
    @Before
    public void setUp() throws Exception {
        periodicalDAO = DBManager.getInstance().getDAOFactory().getPeriodicalDAO();
    }

    @Test
    public void findAll() throws Exception {
        List<Periodical> list = periodicalDAO.findAll();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void findById() throws Exception {
        int id = 2;
        Periodical periodical = periodicalDAO.findById( id );
        System.out.println(periodical.getName());
        Assert.assertNotNull( periodical );
        Assert.assertEquals("Идеальный дом", periodical.getName());
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
    public void findByName() throws Exception {

    }

    @Test
    public void findByCategoryID() throws Exception {
        long category = 2;
        List<Periodical> list = periodicalDAO.findByCategoryID( category );
        System.out.println(list.toString());
        Assert.assertNotNull( list );
        Assert.assertEquals( "Идеальный дом", list.get( 0 ).getName() );
    }

}