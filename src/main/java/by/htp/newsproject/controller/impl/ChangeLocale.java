package by.htp.newsproject.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.htp.newsproject.controller.Command;
import by.htp.newsproject.controller.constant.RequestParam;
import by.htp.newsproject.controller.constant.SessionAttribute;
import by.htp.newsproject.controller.constant.URLPattern;
import by.htp.newsproject.service.ServiceExeption;
import by.htp.newsproject.util.MethodUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChangeLocale implements Command {
	
	private final static MethodUtil methodUtil = new MethodUtil();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final Logger log = LogManager.getRootLogger();

		request.getSession(true).setAttribute(SessionAttribute.LOCALE_SESSION_ATTRIBUTE,
				request.getParameter(SessionAttribute.LOCALE_SESSION_ATTRIBUTE));

		try {
			methodUtil.putNewsListAttribute(request);

			response.sendRedirect(RequestParam.SERVLET_PATTERN_NAME + (String) request.getSession()
					.getAttribute(SessionAttribute.KEY_TO_LAST_REQUEST_URL_SESSION_ATTRIBUTE));

		} catch (ServiceExeption e) {

			log.log(Level.ERROR, "Changing locale goes wrong", e);
			response.sendRedirect(URLPattern.REDIRECT_URL_TO_ERROR_PAGE);
		}
	}

}
