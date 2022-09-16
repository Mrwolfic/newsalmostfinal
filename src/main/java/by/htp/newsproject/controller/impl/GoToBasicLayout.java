package by.htp.newsproject.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.newsproject.controller.Command;
import by.htp.newsproject.controller.constant.URLPattern;
import by.htp.newsproject.service.ServiceExeption;
import by.htp.newsproject.util.MethodUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToBasicLayout implements Command {

	private final static MethodUtil methodUtil = new MethodUtil();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final Logger log = LogManager.getRootLogger();
		
		try {
			
			methodUtil.putNewsListAttribute(request);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher(URLPattern.FORWARD_URL_TO_BASIC_LAYOUT);

			if (requestDispatcher != null) {
				requestDispatcher.forward(request, response);
			} else {
				response.sendRedirect(URLPattern.REDIRECT_URL_TO_ERROR_PAGE);
			}

		} catch (ServiceExeption e) {

			log.log(Level.ERROR, "Something wrong with loading news", e);
			response.sendRedirect(URLPattern.REDIRECT_URL_TO_ERROR_PAGE);
		}
	}
}
