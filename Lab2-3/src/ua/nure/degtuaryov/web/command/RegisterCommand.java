package ua.nure.degtuaryov.web.command;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.log4j.Logger;

import ua.nure.degtuaryov.db.Constants;
import ua.nure.degtuaryov.db.Role;
import ua.nure.degtuaryov.db.dao.DAOFactory;
import ua.nure.degtuaryov.db.entity.Client;
import ua.nure.degtuaryov.web.Path;

/**
 * RegisterCommand command.
 * 
 * @author Degtuaryow
 * 
 */
public class RegisterCommand extends Command {

	private static final String WRONG = "wrong";

	private static final long serialVersionUID = 3900017600039343228L;

	private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		LOGGER.debug("RegisterCommand starts");
		Locale local = new Locale(Config.get(request.getSession(), Config.FMT_LOCALE).toString());
		ResourceBundle rb = ResourceBundle.getBundle("resources", local);
		DAOFactory.setDaoFactoryFCN(Constants.DAO_FACTORY);
		DAOFactory daoFactory = DAOFactory.getInstance();
		String login = request.getParameter("login");
		LOGGER.trace("Request parameter: loging --> " + login);
		String password = request.getParameter("password");
		LOGGER.trace("Request parameter: password --> " + password);
		String email = request.getParameter("email");
		LOGGER.trace("Request parameter: email --> " + email);
		String firstName = request.getParameter("firstName");
		LOGGER.trace("Request parameter: firstname --> " + firstName);
		String secondname = request.getParameter("secondname");
		LOGGER.trace("Request parameter: secondname --> " + secondname);
		String surname = request.getParameter("surname");
		LOGGER.trace("Request parameter: surname --> " + surname);
		String forward = Path.PAGE_ERROR_PAGE;
		Client client = new Client();
		client.setEmail(email);
		client.setFirstName(firstName);
		client.setLogin(login);
		client.setPassword(password);
		client.setRoleId(Role.valueOf("CLIENT").ordinal());
		client.setSecondName(secondname);
		client.setSurname(surname);
		if (login == null || password == null || login.isEmpty() || password.isEmpty() || email == null
				|| email.isEmpty() || firstName == null || firstName.isEmpty() || secondname == null
				|| secondname.isEmpty() || surname == null || surname.isEmpty()) {
			request.setAttribute(WRONG, rb.getString("register_client_page.complete_all_fields"));
			forward = Path.COMMAND_VIEW_REGISTRATION_PAGE;
		} else {
			if (daoFactory.getClientDAO().addClient(client)) {
				request.setAttribute("success", rb.getString("register_client_page.reg_success"));
				LOGGER.debug("RegisterCommand:" + client);
				StringBuilder message = new StringBuilder();
				message.append("Dear," + firstName + " " + surname + ":)\n");
				message.append("\nYou create a new profile in our booking system.\n\t Your login: " + login);
				message.append("\nAdventure begins from us, come and buy your ticket. What time is it? Adventure Time!"
						+ "\n\nSincerely your administration. ");
				forward = Path.COMMAND_VIEW_REGISTRATION_PAGE;
			} else {
				request.setAttribute(WRONG, rb.getString("register_client_page.login_in_use"));
				forward = Path.COMMAND_VIEW_REGISTRATION_PAGE;
			}
		}
		LOGGER.debug("RegisterCommand ends");
		request.setAttribute("client", client);
		return forward;
	}
}