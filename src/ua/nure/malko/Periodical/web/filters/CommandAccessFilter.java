package ua.nure.malko.Periodical.web.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.nure.malko.Periodical.Path;
import ua.nure.malko.Periodical.db.Role;

public class CommandAccessFilter implements Filter {

    // commands access
    private Map<Role, List<String>> accessMap = new HashMap<>();
    private List<String> commons = new ArrayList<>();
    private List<String> outOfControl = new ArrayList<>();

    public void destroy() {
        System.err.println( "Filter destruction finished" );
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.err.println( "CommandAccessFilter starts" );

        if (accessAllowed( request )) {
            System.err.println( "CommandAccessFilter finished" );
            chain.doFilter( request, response );
        } else {
            String errorMessasge = "You do not have permission to access the requested resource";

            request.setAttribute( "errorMessage", errorMessasge );

            System.err.println( "Filter --> You do not have permission to access the requested resource" );
            request.getRequestDispatcher( Path.PAGE_ERROR_PAGE )
                    .forward( request, response );
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter( "command" );
        if (commandName == null || commandName.isEmpty()) {
            return false;
        }

        if (outOfControl.contains( commandName )) {
            return true;
        }

        HttpSession session = httpRequest.getSession( false );
        if (session == null) {
            return false;
        }

        Role userRole = (Role) session.getAttribute( "userRole" );
        if (userRole == null) {
            return false;
        }

        return accessMap.get( userRole ).contains( commandName )
                || commons.contains( commandName );
    }

    public void init(FilterConfig fConfig) throws ServletException {
        System.err.println( "CommandAccessFilter initialization starts" );

        // roles
        accessMap.put( Role.ADMIN, asList( fConfig.getInitParameter( "admin" ) ) );
        accessMap.put( Role.CLIENT, asList( fConfig.getInitParameter( "client" ) ) );

        // commons
        commons = asList( fConfig.getInitParameter( "common" ) );

        // out of control
        outOfControl = asList( fConfig.getInitParameter( "out-of-control" ) );

        System.err.println( "CommandAccessFilter initialization finished" );
    }

    private List<String> asList(String str) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer( str );
        while (st.hasMoreTokens()) {
            list.add( st.nextToken() );
        }
        return list;
    }

}