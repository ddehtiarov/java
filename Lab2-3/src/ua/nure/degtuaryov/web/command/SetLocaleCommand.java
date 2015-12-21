package ua.nure.degtuaryov.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

/**
 * SetLocaleCommand command.
 * 
 * @author Dedtuaryow
 * 
 */
@WebServlet("/setLocale")
public class SetLocaleCommand extends HttpServlet {

	private static final long serialVersionUID = 993617600039343228L;

	private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		LOGGER.debug("SetLocaleCommand starts");
		String localeToSetI18N = request.getParameter("locale");
		HttpSession session = request.getSession();
		if (localeToSetI18N != null && !localeToSetI18N.isEmpty()) {
			LOGGER.debug("SetLocaleCommand changes locale to" + localeToSetI18N);
			Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", localeToSetI18N);
			session.setAttribute("defaultLocale", localeToSetI18N);
			String status = new Gson().toJson("Ok");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(status);
		}
		LOGGER.debug("SetLocaleCommand ends");
	}

}
