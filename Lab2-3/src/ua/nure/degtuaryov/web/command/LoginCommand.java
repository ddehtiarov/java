package ua.nure.degtuaryov.web.command;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.log4j.Logger;

import ua.nure.degtuaryov.db.Constants;
import ua.nure.degtuaryov.db.Role;
import ua.nure.degtuaryov.db.dao.DAOFactory;
import ua.nure.degtuaryov.db.entity.Client;
import ua.nure.degtuaryov.web.Path;

/**
 * Login command.
 * 
 * @author Degtuaryow
 * 
 */
public class LoginCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(LoginCommand.class);
	
	private static final String WRONG = "wrong";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Locale local = new Locale(Config.get(request.getSession(), Config.FMT_LOCALE).toString());
		ResourceBundle rb = ResourceBundle.getBundle("resources", local);
		LOG.debug("Command starts");
		DAOFactory.setDaoFactoryFCN(Constants.DAO_FACTORY);
		DAOFactory daoFactory = DAOFactory.getInstance();
		HttpSession session = request.getSession();
		String login = request.getParameter("login");
		LOG.trace("Request parameter: loging --> " + login);
		String password = request.getParameter("password");
		LOG.trace("Request parameter: password --> " + password);
		String forward = "";
		request.setAttribute("login", login);
		request.setAttribute("password", password);
		Client client = null;
		Role clientRole = null;
		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			request.setAttribute(WRONG, "Please, write your login/password!");
			forward = Path.COMMAND_VIEW_LOGIN_PAGE;
		} else {
			client = daoFactory.getClientDAO().findClientByLoginPass(login, password);
			if (client != null) {
				request.setAttribute("success", "Cool!");
				clientRole = Role.getRole(client);
				LOG.debug("LoginCommand:" + client);
				forward = Path.COMMAND_VIEW_ALL_CLIENTS;
				
			} else {
				request.setAttribute(WRONG, rb.getString("login_client_page.login_error"));
				forward = Path.COMMAND_VIEW_LOGIN_PAGE;
			}
		}
		LOG.debug("Logincommand stops");
		session.setAttribute("client", client);
		session.setAttribute("clientRole", clientRole);
		return forward;
	}

}