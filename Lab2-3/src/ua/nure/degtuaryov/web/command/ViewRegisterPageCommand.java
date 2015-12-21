package ua.nure.degtuaryov.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.degtuaryov.web.Path;

/**
 * ViewRegisterPageCommand command.
 * 
 * @author Dedtuaryow
 * 
 */
public class ViewRegisterPageCommand extends Command {

	private static final long serialVersionUID = 8112886711621987654L;
	
	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.debug("ViewRegisterPageCommand starts");
		LOG.debug("ViewRegisterPageCommand ends");
		return Path.PAGE_REGISTER;
	}

}
