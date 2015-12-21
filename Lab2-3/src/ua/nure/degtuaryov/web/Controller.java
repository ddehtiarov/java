package ua.nure.degtuaryov.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.log4j.Logger;

import ua.nure.degtuaryov.web.command.Command;
import ua.nure.degtuaryov.web.command.CommandContainer;

/**
 * Main servlet controller.
 * 
 * @author Degtuaryow
 * 
 */
@WebServlet(name = "Controller", urlPatterns = { "/controller" })
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 2423353715955164816L;

	private static final Logger LOG = Logger.getLogger(Controller.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * Main method of this controller.
	 */
	private void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if (request.getSession().getAttribute("defaultLocale") == null) {
			Config.set(request.getSession(), "javax.servlet.jsp.jstl.fmt.locale", "en");
			request.getSession().setAttribute("defaultLocale", "en");
		}
		LOG.debug("Controller starts");
		String commandName = request.getParameter("command");
		System.out.println(commandName);
		LOG.trace("Request parameter: command --> " + commandName);
		Command command = CommandContainer.get(commandName);
		LOG.trace("Obtained command --> " + command);
		String forward = Path.PAGE_ERROR_PAGE;
		if (request.getParameter("id") != null) {
			Long id = 0L;
			try {
				if (request.getParameter("id").matches("[0-9]+")) {
					id = Long.valueOf(request.getParameter("id"));
				} else {
					id = 0l;
				}
			} catch (Exception ex) {
				LOG.error(ex.getMessage());
			}
			if (id < 0) {
				id *= (-1);
				System.out.println(id);
			}
			request.setAttribute("id", id);
		}
		try {
			forward = command.execute(request, response);
		} catch (Exception ex) {
			request.setAttribute("errorMessage", ex.getMessage());
		}
		LOG.trace("Forward address --> " + forward);
		LOG.debug("Controller finished, now go to forward address --> " + forward);
		if (commandName.contains(".do") && request.getAttribute("success") != null) {
			response.sendRedirect(request.getContextPath() + forward);
		} else {
			request.getRequestDispatcher(forward).forward(request, response);
		}
	}

}