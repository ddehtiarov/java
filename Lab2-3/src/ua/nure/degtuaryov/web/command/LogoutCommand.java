package ua.nure.degtuaryov.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.degtuaryov.web.Path;

/**
 * Logout command.
 * 
 * @author Dedtuaryow
 * 
 */
public class LogoutCommand extends Command {

	private static final long serialVersionUID = -2785971616686657297L;

	private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Command starts");
		
		HttpSession sesn = request.getSession(false);
		if (sesn != null) {
			sesn.setAttribute("client", null);
			sesn.setAttribute("clientRole", null);
			sesn.setAttribute("defaultLocale", null);
			sesn.invalidate();
		}
		request.setAttribute("success", "logout");
		LOG.debug("Command finished");
		return Path.PAGE_INDEX;
	}

}