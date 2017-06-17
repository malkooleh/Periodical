package ua.nure.malko.Periodical.web.commands;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.malko.Periodical.Path;
import ua.nure.malko.Periodical.db.DBManager;
import ua.nure.malko.Periodical.db.bean.UserSubscriptionBean;
import ua.nure.malko.Periodical.exception.AppException;

/**
 * Lists orders.
 */
public class ListSubscriptionsCommand extends Command {

    private static final long serialVersionUID = 116L;


    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {
        System.err.println( "Commands starts" );

        List<UserSubscriptionBean> userSubscriptionBeanList = DBManager.getInstance().getDAOFactory().getSubscriptionDAO().getUserSubscriptionBeans();
        System.err.println( "Found in DB: userSubscriptionBeanList");

        // put user order beans list to request
        request.setAttribute( "userSubscriptionBeanList", userSubscriptionBeanList );
        System.err.println( "Set the request attribute: userSubscriptionBeanList --> " + userSubscriptionBeanList );

        System.err.println( "Commands finished" );
        return Path.PAGE_LIST_SUBSCRIPTIONS;
    }

}