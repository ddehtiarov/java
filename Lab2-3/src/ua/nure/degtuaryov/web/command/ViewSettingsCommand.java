package ua.nure.degtuaryov.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.degtuaryov.web.Path;

/**
 * View settings command.
 * 
 * @author Degtuaryow
 * 
 */
public class ViewSettingsCommand extends Command {
	
	private static final long serialVersionUID = -31536593627692473L;
	
	private static final Logger LOG = Logger.getLogger(ViewSettingsCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {		
		LOG.debug("ViewSettingsCommand starts");

		LOG.debug("ViewSettingsCommand finished");
		return Path.PAGE_SETTINGS;
	}

}