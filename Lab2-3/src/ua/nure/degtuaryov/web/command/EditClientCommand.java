package ua.nure.degtuaryov.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.log4j.Logger;

import ua.nure.degtuaryov.db.Constants;
import ua.nure.degtuaryov.db.dao.DAOFactory;
import ua.nure.degtuaryov.db.entity.Client;
import ua.nure.degtuaryov.web.Path;

/**
 * EditClientCommand command.
 * 
 * @author Dedtuaryow
 * 
 */
public class EditClientCommand extends Command {
	private static final String WRONG = "wrong";

	private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

	private static final long serialVersionUID = 6301368965415920437L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOGGER.debug("EditClientCommand starts");
		String forward = Path.PAGE_ERROR_PAGE;
		Long id = (Long) request.getAttribute("id");
		if (request.getAttribute("id") == null || request.getAttribute("id").toString().isEmpty()) {
			return forward;
		} else {
			String clientStr = "client";
			DAOFactory.setDaoFactoryFCN(Constants.DAO_FACTORY);
			DAOFactory daoFactory = DAOFactory.getInstance();
			String login = request.getParameter("login");
			String role = request.getParameter("role");
			System.out.println("role" + role);
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String firstName = request.getParameter("firstName");
			String secondname = request.getParameter("secondName");
			String surname = request.getParameter("surname");
			Client client = new Client();
			client.setEmail(email);
			client.setFirstName(firstName);
			client.setLogin(login);
			client.setPassword(password);
			client.setRoleId(Integer.valueOf(role));
			client.setSecondName(secondname);
			client.setSurname(surname);
			client.setId(id);
			HttpSession session = request.getSession();
			System.out.println("CangeCommand#client :" + client);
			if (role == null || password == null || login.isEmpty() || password.isEmpty() || email == null
					|| email.isEmpty() || firstName == null || firstName.isEmpty() || secondname == null
					|| secondname.isEmpty() || surname == null || surname.isEmpty()) {
				request.setAttribute(WRONG, "Please, complete all the fields and continue!");
			} else {
				if (daoFactory.getClientDAO().updateClient(client)) {
					request.setAttribute("success", "You update info!");
					if (((Client) session.getAttribute(clientStr)).getRoleId() == 0
							&& !client.getLogin().equals(((Client) session.getAttribute(clientStr)).getLogin())) {
						forward = Path.COMMAND_EDIT_CLIENT + client.getId();
					} else {
						forward = Path.COMMAND_VIEW_SETTINGS;
						request.getSession(false).setAttribute(clientStr, client);
					}
				} else {
					request.setAttribute(WRONG, "Error");
				}
				request.setAttribute(clientStr, client);
			}
			String localeToSetI18N = request.getParameter("locale");
			if (localeToSetI18N != null && !localeToSetI18N.isEmpty()) {
				Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", localeToSetI18N);
				session.setAttribute("defaultLocale", localeToSetI18N);
			}
		}
		LOGGER.debug("EditClientCommand ends");
		return forward;
	}

}
