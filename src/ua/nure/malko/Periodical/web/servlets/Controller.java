package ua.nure.malko.Periodical.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.malko.Periodical.Path;
import ua.nure.malko.Periodical.exception.AppException;
import ua.nure.malko.Periodical.web.commands.Command;
import ua.nure.malko.Periodical.web.commands.CommandContainer;

/**
 * Main servlet controller.
 */

public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = -1L;

	//private static final Logger LOG = Logger.getLogger(Controller.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * Main method of this controller.
	 */
	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		//LOG.debug("Controller starts");

		// extract command name from the request
		String commandName = request.getParameter("command");
		//LOG.trace("Request parameter: command --> " + commandName);

		// obtain command object by its name
		Command command = CommandContainer.get(commandName);
		//LOG.trace("Obtained command --> " + command);

		// execute command and get forward address
		String forward = Path.PAGE_ERROR_PAGE;
		try {
			forward = command.execute(request, response);
		} catch (AppException ex) {
			request.setAttribute("errorMessage", ex.getMessage());
		}

		// go to forward
		request.getRequestDispatcher(forward).forward(request, response);
	}

}