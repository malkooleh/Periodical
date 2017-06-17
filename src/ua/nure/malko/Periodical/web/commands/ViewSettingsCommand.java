package ua.nure.malko.Periodical.web.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import ua.nure.malko.Periodical.Path;

/**
 * View settings command.
 */
public class ViewSettingsCommand extends Command {

    private static final long serialVersionUID = 114L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        System.err.println( "Command starts" );

        System.err.println( "Command finished" );
        return Path.PAGE_SETTINGS;
    }

}