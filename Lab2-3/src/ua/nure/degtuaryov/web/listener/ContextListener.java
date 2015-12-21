package ua.nure.degtuaryov.web.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Context listener.
 * 
 * @author Degtuaryow
 * 
 */
public class ContextListener implements ServletContextListener {

	private static final Logger LOGER = Logger.getLogger(ContextListener.class);

	public void contextDestroyed(ServletContextEvent event) {
		logMessage("Servlet context destruction starts");
		logMessage("Servlet context destruction finished");
	}

	public void contextInitialized(ServletContextEvent event) {
		logMessage("Servlet context initialization starts");

		ServletContext servletContext = event.getServletContext();
		initLog4J(servletContext);
		initCommandContainer();
		initI18N(servletContext);
	
		logMessage("Servlet context initialization finished");
	}

	/**
	 * Initializes log4j framework.
	 * 
	 * @param servletContext
	 */
	private void initLog4J(ServletContext servletContext) {
		logMessage("Log4J initialization started");
		try {
			PropertyConfigurator.configure(
				servletContext.getRealPath("WEB-INF/log4j.properties"));
			LOGER.debug("Log4j has been initialized");
		} catch (Exception ex) {
			logMessage("Cannot configure Log4j");
			LOGER.error(ex.getMessage());
		}		
		logMessage("Log4J initialization finished");
	}
	
	/**
	 * Initializes CommandContainer.
	 * 
	 * @param servletContext
	 */
	private void initCommandContainer() {
		
		try {
			Class.forName("ua.nure.degtuaryov.web.command.CommandContainer");
		} catch (ClassNotFoundException ex) {
			throw new IllegalStateException("Cannot initialize Command Container");
		}
	}
	
	private void logMessage(String msg) {
		System.out.println("[ContextListener] " + msg);
	}
	
	/**
	 * Initializes i18n subsystem.
	 */
	private void initI18N(ServletContext servletContext) {
		LOGER.debug("I18N subsystem initialization started");
		
		String localesVal = servletContext.getInitParameter("locale");
		if (localesVal == null || localesVal.isEmpty()) {
			LOGER.warn("'locale' init parameter is empty, the default encoding will be used");
		} else {
			List<String> locales = new ArrayList<String>();
			StringTokenizer st = new StringTokenizer(localesVal);
			while (st.hasMoreTokens()) {
				String localeName = st.nextToken();
				locales.add(localeName);
			}								
			LOGER.debug("Application attribute set: locale --> " + locales);
			servletContext.setAttribute("locale", locales);
		}		
		
		LOGER.debug("I18N subsystem initialization finished");
	}

}