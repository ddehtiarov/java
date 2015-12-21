package ua.nure.degtuaryov.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * Encoding filter.
 * 
 * @author Dedtuaryow
 * 
 */
public class EncodingFilter implements Filter {

	private static final Logger LOGER = Logger.getLogger(EncodingFilter.class);

	private String encoding;

	public void destroy() {
		LOGER.debug("Filter destruction starts");
		LOGER.debug("Filter destruction finished");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		LOGER.debug("Filter starts");
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		LOGER.trace("Request uri --> " + httpRequest.getRequestURI());
		
		String reqEncoding = request.getCharacterEncoding();
		if (reqEncoding == null) {
			LOGER.trace("Request encoding = null, set encoding --> " + encoding);
			request.setCharacterEncoding(encoding);
		}
		
		LOGER.debug("Filter finished");		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		LOGER.debug("Filter initialization starts");
		encoding = fConfig.getInitParameter("encoding");
		LOGER.trace("Encoding from web.xml --> " + encoding);
		LOGER.debug("Filter initialization finished");
	}

}