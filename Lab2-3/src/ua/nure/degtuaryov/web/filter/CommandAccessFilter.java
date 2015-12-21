package ua.nure.degtuaryov.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.degtuaryov.db.Role;
import ua.nure.degtuaryov.web.Path;

/**
 * Security filter.
 * 
 * @author Degtuaryow
 * 
 */
public class CommandAccessFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(CommandAccessFilter.class);

	// commands access
	private Map<Role, List<String>> accessRoleMap = new HashMap<Role, List<String>>();
	private List<String> commons = new ArrayList<String>();
	private List<String> users = new ArrayList<String>();

	public void destroy() {
		LOGGER.debug("Filter destruction starts");
		LOGGER.debug("Filter destruction finished");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOGGER.debug("Filter starts");

		if (accessAllowed(request)) {
			LOGGER.debug("Filter finished");
			chain.doFilter(request, response);
		} else {
			String errorMessasge = "You do not have permission to access the requested resource!";

			request.setAttribute("errorMessage", errorMessasge);
			LOGGER.trace("Set the request attribute: errorMessage --> " + errorMessasge);

			request.getRequestDispatcher(Path.PAGE_ERROR_PAGE).forward(request, response);
		}
	}

	private boolean accessAllowed(ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String commandName = request.getParameter("command");
		LOGGER.trace("Command -->" + commandName);
		if (commandName == null || commandName.isEmpty()) {
			return false;
		}
		HttpSession session = httpRequest.getSession(false);
		LOGGER.trace("Session from request -->" + session);
		if (session == null) {
			return users.contains(commandName) || commons.contains(commandName);
		} else {
			Role clientRole = (Role) session.getAttribute("clientRole");
			LOGGER.trace("clientRole -->" + clientRole);
			if (clientRole == null) {
				return users.contains(commandName) || commons.contains(commandName);
			} else {
				if (!users.contains(commandName)) {
					if (clientRole.compareTo(Role.CLIENT) == 0) {
						return accessRoleMap.get(clientRole).contains(commandName) || commons.contains(commandName);
					} else {
						return true;
					}
				} else {
					return false;
				}
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		LOGGER.debug("Filter initialization starts");

		// roles
		accessRoleMap.put(Role.ADMIN, asList(fConfig.getInitParameter("admin")));
		accessRoleMap.put(Role.CLIENT, asList(fConfig.getInitParameter("client")));
		LOGGER.trace(accessRoleMap.get(Role.ADMIN));
		LOGGER.trace("Access map --> " + accessRoleMap);

		// commons
		commons = asList(fConfig.getInitParameter("common"));
		LOGGER.trace("Common commands --> " + commons);

		// user
		users = asList(fConfig.getInitParameter("user"));
		LOGGER.trace("users commands --> " + users);

		LOGGER.debug("Filter initialization finished");
	}

	/**
	 * Extracts parameter values from string.
	 * 
	 * @param elem
	 *            parameter values string.
	 * @return list of parameter values.
	 */
	private List<String> asList(String elem) {
		List<String> listTock = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(elem);
		while (st.hasMoreTokens()) {
			listTock.add(st.nextToken());
		}
		return listTock;
	}

}