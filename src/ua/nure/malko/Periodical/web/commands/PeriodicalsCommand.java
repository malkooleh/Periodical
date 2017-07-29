package ua.nure.malko.Periodical.web.commands;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.malko.Periodical.Path;
import ua.nure.malko.Periodical.db.DBManager;
import ua.nure.malko.Periodical.db.entity.Category;
import ua.nure.malko.Periodical.db.entity.Periodical;
import ua.nure.malko.Periodical.exception.AppException;
import ua.nure.malko.Periodical.exception.DBException;

/**
 * Lists menu items.
 */
public class PeriodicalsCommand extends Command {

    private static final long serialVersionUID = 115L;

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        System.err.println( "Command starts" );

        String categoryId = request.getParameter( "category_id" );
        System.err.println( "category_id = " + categoryId);

        // get periodicals list
        List<Periodical> periodicals = getListPeriodicals( categoryId, request );
        System.err.println("List<Periodical> periodicals - " + periodicals.size() );
        List<Category> categories = DBManager.getInstance().getDAOFactory().getCategoryDAO().findAll();
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

    private List<Periodical> getListPeriodicals(String categoryId, HttpServletRequest request) throws DBException {
        List<Periodical> list;
        if (categoryId == null || Long.parseLong( categoryId ) == 0) {
            list = DBManager.getInstance().getDAOFactory().getPeriodicalDAO().findAll();
        } else if (request.getAttribute( "periodicals" ) == null){
            list = DBManager.getInstance().getDAOFactory().getPeriodicalDAO().findByCategoryID( Long.parseLong( categoryId ) );
        } else {
            list = (List<Periodical>) request.getAttribute( "periodicals" );
        }
        return list;
    }

}