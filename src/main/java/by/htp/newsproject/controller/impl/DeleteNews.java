package by.htp.newsproject.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.newsproject.controller.Command;
import by.htp.newsproject.controller.constant.RequestParam;
import by.htp.newsproject.controller.constant.URLPattern;
import by.htp.newsproject.service.NewsService;
import by.htp.newsproject.service.ServiceExeption;
import by.htp.newsproject.service.ServiceFactory;
import by.htp.newsproject.util.MethodUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteNews implements Command {
	
	private final static MethodUtil methodUtil = new MethodUtil();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final Logger log = LogManager.getRootLogger();
		
		NewsService newsService = ServiceFactory.getInstance().getNewsservice();
		
		final String[] checkboxParamValues = request.getParameterValues(RequestParam.CHECKBOX_PARAM_NAME);

		try {
			if (checkboxParamValues != null) {
				newsService.deleteChosenNews(checkboxParamValues);
				methodUtil.putNewsListAttribute(request);
				response.sendRedirect(URLPattern.REDIRECT_URL_TO_BASIC_LAYOUT);
			} else {
				response.sendRedirect(URLPattern.REDIRECT_URL_TO_BASIC_LAYOUT);
			}

		} catch (ServiceExeption e) {

			log.log(Level.ERROR, "Something wrong with saving news", e);
			response.sendRedirect(URLPattern.REDIRECT_URL_TO_ERROR_PAGE);
		}
	}
}
