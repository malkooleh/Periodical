package ua.nure.malko.Periodical.web.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Context listener.
 */

public class ContextListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent event) {
        System.err.println( "Servlet context destruction starts" );
    }

    public void contextInitialized(ServletContextEvent event) {
        System.err.println( "Servlet context initialization starts" );

        //ServletContext servletContext = event.getServletContext();
        initCommandContainer();

        System.err.println( "Servlet context initialization finished" );
    }

    private void initCommandContainer() {

        // initialize commands container
        // just load class to JVM
        try {
            Class.forName( "ua.nure.malko.Periodical.web.commands.CommandContainer" );
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException( "Cannot initialize Command Container" );
        }
    }

}