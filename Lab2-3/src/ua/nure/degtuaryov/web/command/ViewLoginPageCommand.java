package ua.nure.degtuaryov.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.degtuaryov.web.Path;

/**
 * ViewLoginPageCommand command.
 * 
 * @author Dedtuaryow
 * 
 */
public class ViewLoginPageCommand extends Command {

	private static final long serialVersionUID = 1112863421621049040L;
	
	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.debug("ViewLoginPageCommand starts");
		LOG.debug("ViewLoginPageCommand ends");
		return Path.PAGE_LOGIN;
	}

}
