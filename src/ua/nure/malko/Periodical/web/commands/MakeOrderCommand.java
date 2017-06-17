package ua.nure.malko.Periodical.web.commands;

import ua.nure.malko.Periodical.Path;
import ua.nure.malko.Periodical.db.DBManager;
import ua.nure.malko.Periodical.db.entity.Periodical;
import ua.nure.malko.Periodical.db.entity.Subscription;
import ua.nure.malko.Periodical.db.entity.User;
import ua.nure.malko.Periodical.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MakeOrderCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        System.err.println( "MakeOrderCommand starts" );

        DBManager dbManager = DBManager.getInstance();
        List<Periodical> periodicals = new ArrayList<>(  );
        String[] items = request.getParameterValues( "itemId" );
        User us = (User) request.getSession().getAttribute( "user" );
        System.err.println( "User --> " + us);
        for (String item : items) {
            periodicals.add( DBManager.getInstance().getDAOFactory().getPeriodicalDAO().findById( Long.valueOf( item )));
        }

        Subscription subscription = new Subscription();
        int bill = getPrice( periodicals );
        System.err.println( "price " + bill );

        subscription.setBill( bill );
        subscription.setUserId( us.getId() );
        subscription.setStatusId( 0 );

        dbManager.getDAOFactory().getSubscriptionDAO().insert( subscription );
        System.err.println( "insert done" );

        Subscription userSubscription = dbManager.getDAOFactory().getSubscriptionDAO().findByStatusAndUser( us.getId(), 0 );
        System.err.println( "userSubscription " + userSubscription );

        request.setAttribute( "userOrder", userSubscription );

        System.err.println( "MakeOrderCommand finished" );

        return Path.PAGE_USER_SUBSCRIPTION;
    }

    private int getPrice(List<Periodical> periodicals){
        int bill = 0;
        for (Periodical per : periodicals) {
            bill += per.getPrice();
        }
        return bill;
    }
}
