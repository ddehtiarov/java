package ua.nure.degtuaryov.web;


/**
 * Path constants from my app
 * 
 * @author Degtuaryow
 *
 */
public class Path {
	// pages
	public static final String PAGE_INDEX = "/";
	public static final String PAGE_LOGIN = "/WEB-INF/jsp/user/login.jsp";
	public static final String PAGE_REGISTER = "/WEB-INF/jsp/user/register.jsp";
	public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
	public static final String PAGE_SETTINGS = "/WEB-INF/jsp/client/settings.jsp";
	public static final String PAGE_SHOW_ALL_CLIENTS = "/WEB-INF/jsp/client/showAllClients.jsp";
	public static final String PAGE_EDIT_CLIENT = "/WEB-INF/jsp/admin/editClient.jsp";
	public static final String PAGE_DELETE_CLIENT = "/WEB-INF/jsp/admin/deleteClient.jsp";
	// commands
	public static final String COMMAND_VIEW_LOGIN_PAGE = "/controller?command=viewLoginPage";
	public static final String COMMAND_VIEW_REGISTRATION_PAGE = "/controller?command=viewRegisterPage";
	public static final String COMMAND_VIEW_ALL_CLIENTS = "/controller?command=viewAllClients";
	public static final String COMMAND_EDIT_CLIENT = "/controller?command=editClientPage&id=";
	public static final String COMMAND_VIEW_SETTINGS = "/controller?command=viewSettings";
}
