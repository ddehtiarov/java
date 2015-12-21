package ua.nure.degtuaryov.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.degtuaryov.web.Path;


/**
 * No command.
 * 
 * @author Dedtuaryow
 * 
 */
public class NoCommand extends Command {

	private static final long serialVersionUID = 665724545L;

	private static final Logger LOGGER = Logger.getLogger(NoCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("Command starts");
		
		String errorMessage = "No such command";
		request.setAttribute("errorMessage", errorMessage);
		LOGGER.error("Set the request attribute: errorMessage --> " + errorMessage);

		LOGGER.debug("Command finished");
		return Path.PAGE_ERROR_PAGE;
	}

}