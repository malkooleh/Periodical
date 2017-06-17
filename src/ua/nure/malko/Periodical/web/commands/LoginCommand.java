package ua.nure.malko.Periodical.web.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.malko.Periodical.Path;
import ua.nure.malko.Periodical.db.DBManager;
import ua.nure.malko.Periodical.db.Role;
import ua.nure.malko.Periodical.db.entity.User;
import ua.nure.malko.Periodical.exception.AppException;

public class LoginCommand extends Command {

	private static final long serialVersionUID = 111L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		System.err.println("LoginCommand starts");

		HttpSession session = request.getSession();

		// obtain login and password from a request
		DBManager dbManager = DBManager.getInstance();
		String login = request.getParameter("login");

		String password = request.getParameter("password");
		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			throw new AppException("Login/password cannot be empty");
		}

		User user = dbManager.getDAOFactory().getUserDAO().findByLogin( login );

		if (user == null || !password.equals(user.getPassword())) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			throw new AppException("Cannot find user with such login/password");
		}

		response.setStatus( HttpServletResponse.SC_OK );

		Role userRole = Role.getRole(user);
		
		String forward = Path.PAGE_ERROR_PAGE;

		if (userRole == Role.ADMIN) {
			forward = Path.COMMAND_LIST_SUBSCRIPTION;
		}

		if (userRole == Role.CLIENT) {
			forward = Path.COMMAND_LIST_PERIODICAL;
		}

		session.setAttribute("user", user);

		session.setAttribute("userRole", userRole);

		System.err.println("LoginCommand finished");
		return forward;
	}

}