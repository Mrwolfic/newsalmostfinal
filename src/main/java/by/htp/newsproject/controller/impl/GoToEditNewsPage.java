package by.htp.newsproject.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.newsproject.controller.Command;
import by.htp.newsproject.controller.bean.NewsBean;
import by.htp.newsproject.controller.constant.RequestParam;
import by.htp.newsproject.controller.constant.URLPattern;
import by.htp.newsproject.service.ServiceExeption;
import by.htp.newsproject.service.ServiceFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToEditNewsPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final Logger log = LogManager.getRootLogger();
		
		int newsID;
		NewsBean news;

		try {
			newsID = Integer.parseInt(request.getParameter(RequestParam.KEY_NEWS_ID));
			news = ServiceFactory.getInstance().getNewsservice().readOneNews(newsID);
			
			request.setAttribute(RequestParam.KEY_TO_NEWS_ATTRIBUTE, RequestParam.VALUE_TO_EDIT_NEWS_ATTRIBUTE);
			request.setAttribute(RequestParam.KEY_TO_EDIT_NEWS_OBJECT, news);
			request.setAttribute(RequestParam.KEY_NEWS_ID, newsID);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher(URLPattern.FORWARD_URL_TO_BASIC_LAYOUT);

			if (requestDispatcher != null) {
				requestDispatcher.forward(request, response);
			} else {
				response.sendRedirect(URLPattern.REDIRECT_URL_TO_ERROR_PAGE);
			}

		} catch (NumberFormatException e) {
			
			log.log(Level.ERROR, "Problems with parsing String parameter to int", e);
			response.sendRedirect(URLPattern.REDIRECT_URL_TO_ERROR_PAGE);
		} catch (ServiceExeption e) {
			
			log.log(Level.ERROR, "Problems with Edit command", e);
			response.sendRedirect(URLPattern.REDIRECT_URL_TO_ERROR_PAGE);
		}

	}

}
