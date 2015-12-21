package ua.nure.degtuaryov.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.degtuaryov.db.Constants;
import ua.nure.degtuaryov.db.dao.DAOFactory;
import ua.nure.degtuaryov.db.entity.Client;
import ua.nure.degtuaryov.web.Path;

/**
 * ViewClientsListCommand command.
 * 
 * @author Dedtuaryow
 * 
 */
public class ViewClientsListCommand extends Command {

	private static final long serialVersionUID = 515513300221847651L;
	
	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.debug("ViewClientsListCommand starts");
		DAOFactory.setDaoFactoryFCN(Constants.DAO_FACTORY);
		DAOFactory daoFactory = DAOFactory.getInstance();
		List<Client> clients = daoFactory.getClientDAO().findAllClients();
		if (clients.isEmpty()) {
			request.setAttribute("error", "Try again later, we have some errors at the server!");
		}
		request.setAttribute("clients", clients);
		LOG.debug("ViewClientsListCommand ends");
		return Path.PAGE_SHOW_ALL_CLIENTS;
	}

}
