package ua.nure.malko.Periodical.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class EncodingFilter implements Filter {

    private static final Logger LOG = Logger.getLogger( EncodingFilter.class );

    private String encoding;

    public void destroy() {
        System.err.println( "Filter destruction finished" );
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        System.err.println( "Filter starts" );

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        System.err.println( "Request uri --> " + httpRequest.getRequestURI() );

        String requestEncoding = request.getCharacterEncoding();
        if (requestEncoding == null || !requestEncoding.equals( encoding )) {
            System.err.println( "Request encoding = " + requestEncoding + ", set encoding --> " + encoding );
            request.setCharacterEncoding( encoding );
        }

        System.err.println( "Filter finished" );
        chain.doFilter( request, response );
    }

    public void init(FilterConfig fConfig) throws ServletException {
        System.err.println( "Filter initialization starts" );
        encoding = fConfig.getInitParameter( "encoding" );
        LOG.trace( "Encoding from web.xml --> " + encoding );
        System.err.println( "Encoding from web.xml --> " + encoding );
        System.err.println( "Filter initialization finished" );
    }

}