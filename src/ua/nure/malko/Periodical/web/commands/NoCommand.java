package ua.nure.malko.Periodical.web.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.malko.Periodical.Path;

/**
 * No command.
 */
public class NoCommand extends Command {

	private static final long serialVersionUID = 113L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("Command starts");
		
		String errorMessage = "No such command";
		request.setAttribute("errorMessage", errorMessage);
		System.err.println("Set the request attribute: errorMessage --> " + errorMessage);

		System.err.println("Command finished");
		return Path.PAGE_ERROR_PAGE;
	}

}