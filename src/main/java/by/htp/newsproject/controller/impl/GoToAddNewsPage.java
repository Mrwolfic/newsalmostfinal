package by.htp.newsproject.controller.impl;

import java.io.IOException;

import by.htp.newsproject.controller.Command;
import by.htp.newsproject.controller.constant.RequestParam;
import by.htp.newsproject.controller.constant.URLPattern;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToAddNewsPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute(RequestParam.KEY_TO_NEWS_ATTRIBUTE, RequestParam.VALUE_TO_ADD_NEWS_ATTRIBUTE);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(URLPattern.FORWARD_URL_TO_BASIC_LAYOUT);

		if (requestDispatcher != null) {
			requestDispatcher.forward(request, response);
		} else {
			response.sendRedirect(URLPattern.REDIRECT_URL_TO_ERROR_PAGE);
		}
	}
}
