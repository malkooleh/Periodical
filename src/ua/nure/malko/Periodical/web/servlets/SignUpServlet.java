package ua.nure.malko.Periodical.web.servlets;

import ua.nure.malko.Periodical.db.DBManager;
import ua.nure.malko.Periodical.db.Fields;
import ua.nure.malko.Periodical.db.entity.User;
import ua.nure.malko.Periodical.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {

    private DBManager dbManager = DBManager.getInstance();

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        perform( request, response );
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        perform( request, response );
    }

    private void perform(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter( Fields.USER_LOGIN );
        String pass = request.getParameter( Fields.USER_PASSWORD );
        response.setContentType( "text/html;charset=utf-8" );

        User newUser;
        User profile;
        try {
            profile = dbManager.getDAOFactory().getUserDAO().findByLogin( login );

            if (profile != null) {
                newUser = new User();
                String fName = request.getParameter( Fields.USER_FIRST_NAME );
                String lName = request.getParameter( Fields.USER_LAST_NAME );

                newUser.setLogin( login );
                newUser.setPassword( pass );
                newUser.setFirstName(fName);
                newUser.setLastName( lName );
                newUser.setRoleId( 1 );

                dbManager.getDAOFactory().getUserDAO().insert( newUser );

            } else {
                response.getWriter().write( "Unauthorized, " + profile.getLogin() + " is exist" );
                System.err.println("Unauthorized");
            }
        } catch (AppException e) {
            System.err.println( e.getMessage());
        }
        response.setStatus( HttpServletResponse.SC_OK );
        request.getRequestDispatcher( "/login.jsp" ).forward( request, response );
    }

}
