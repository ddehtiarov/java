package ua.nure.degtuaryov.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.degtuaryov.db.Constants;
import ua.nure.degtuaryov.db.dao.DAOFactory;
import ua.nure.degtuaryov.web.Path;

/**
 * DeleteClientCommand command.
 * 
 * @author Dedtuaryow
 * 
 */
public class DeleteClientCommand extends Command {

	private static final long serialVersionUID = 987128036708L;

	private static final Logger LOGER = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOGER.debug("DeleteClientCommand starts");
		String forward = Path.COMMAND_VIEW_ALL_CLIENTS;
		if (request.getAttribute("id") == null || request.getAttribute("id").toString().isEmpty()) {
			return forward;
		} else {
			Long id = (Long) request.getAttribute("id");
			DAOFactory.setDaoFactoryFCN(Constants.DAO_FACTORY);
			DAOFactory daoFactory = DAOFactory.getInstance();
			if (daoFactory.getClientDAO().removeClient(id)) {
				request.setAttribute("success", "success");
				forward = Path.COMMAND_VIEW_ALL_CLIENTS;
			}
			LOGER.debug("DeleteClientCommand starts");
		}
		return forward;
	}

}
