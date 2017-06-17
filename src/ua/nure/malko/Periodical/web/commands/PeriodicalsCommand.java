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

/**
 * Lists menu items.
 */
public class PeriodicalsCommand extends Command {

    private static final long serialVersionUID = 115L;

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        System.err.println( "Command starts" );

        // get periodicals list
        List<Periodical> periodicals = DBManager.getInstance().getDAOFactory().getPeriodicalDAO().findAll();
        List<Category> categories = DBManager.getInstance().getDAOFactory().getCategoryDAO().findAll();
        // sort periodicals by category
        Collections.sort( periodicals, new Comparator<Periodical>() {
            public int compare(Periodical o1, Periodical o2) {
                return (int) (o1.getCategoryId() - o2.getCategoryId());
            }
        } );

        // put periodicals list to the request
        request.setAttribute( "periodicals", periodicals );
        request.setAttribute( "categories", categories );

        System.err.println( "Command finished" );
        return Path.PAGE_LIST_PERIODICALS;
    }

}