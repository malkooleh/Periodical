package ua.nure.malko.Periodical.web.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import ua.nure.malko.Periodical.Path;

/**
 * Logout command.
 */
public class LogoutCommand extends Command {

	private static final long serialVersionUID = 112L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("Command starts");
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		System.err.println("Command finished");
		return Path.PAGE_LOGIN;
	}

}