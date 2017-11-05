package ua.nure.malko.Periodical.web.commands;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.malko.Periodical.Path;
import ua.nure.malko.Periodical.db.DBManager;
import ua.nure.malko.Periodical.db.dao.DAOFactory;
import ua.nure.malko.Periodical.db.entity.Category;
import ua.nure.malko.Periodical.db.entity.Periodical;
import ua.nure.malko.Periodical.exception.AppException;
import ua.nure.malko.Periodical.exception.DBException;

/**
 * Lists menu items.
 */
public class PeriodicalsCommand extends Command {

    private static final long serialVersionUID = 115L;
    private final DAOFactory daoFactory = DBManager.getInstance().getDAOFactory();

    PeriodicalsCommand() throws DBException {
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        System.err.println( "PeriodicalsCommand Command starts" );
        String searchBy;
        // get periodicals list
        List<Periodical> periodicals = getPeriodicals( request );

        System.err.println( "List<Periodical> periodicals - " + periodicals.size() );

        List<Category> categories = daoFactory.getCategoryDAO().findAll();
        // sort periodicals by category
        Collections.sort( periodicals, new Comparator<Periodical>() {
            public int compare(Periodical o1, Periodical o2) {
                return (int) (o1.getCategoryId() - o2.getCategoryId());
            }
        } );

        // put periodicals list to the request
        request.getSession( false ).setAttribute( "periodicals", periodicals );
        request.setAttribute( "categories", categories );

        System.err.println( "Command finished" );
        return Path.PAGE_LIST_PERIODICALS;
    }

    private List<Periodical> getPeriodicals(HttpServletRequest request) throws DBException, UnsupportedEncodingException {
        String searchBy;
        List<Periodical> periodicals;
        if (request.getParameter( "search_string" ) != null) {
            searchBy = new String(request.getParameter( "search_string" ).getBytes("ISO-8859-1"),"utf-8");
            System.err.println( "Search string - " + searchBy );
            periodicals = daoFactory.getPeriodicalDAO().findBySearch( searchBy );
            request.setAttribute( "search_string", searchBy );
        } else if (request.getParameter( "category_id" ) != null && Long.parseLong( request.getParameter( "category_id" ) ) != 0) {
            searchBy = request.getParameter( "category_id" );
            periodicals = daoFactory.getPeriodicalDAO().findByCategoryID( Long.parseLong( searchBy ) );
            request.setAttribute( "category_id", searchBy );
        } else {
            periodicals = daoFactory.getPeriodicalDAO().findAll();
        }
        return periodicals;
    }

}