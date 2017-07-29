package ua.nure.malko.Periodical.web.servlets;

import ua.nure.malko.Periodical.db.entity.Periodical;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class ShowImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest( request, response );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest( request, response );
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/jpeg");
        try (OutputStream out = response.getOutputStream()) {
            int index = Integer.valueOf( request.getParameter( "index" ) );
            System.err.println( "index = " + index );
            ArrayList<Periodical> list = (ArrayList<Periodical>)request.getSession( false ).getAttribute( "periodicals" );
            Periodical periodical = list.get( index );
            System.err.println( "Periodical name" + periodical.getName() );
            response.setContentLength( periodical.getImage().length );
            out.write( periodical.getImage() );
        }
    }
}
