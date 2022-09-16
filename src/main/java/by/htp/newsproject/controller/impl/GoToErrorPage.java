package by.htp.newsproject.controller.impl;

import java.io.IOException;

import by.htp.newsproject.controller.Command;
import by.htp.newsproject.controller.constant.URLPattern;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToErrorPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(URLPattern.FORWARD_URL_TO_ERROR_PAGE);

		if (requestDispatcher != null) {
			requestDispatcher.forward(request, response);
		} else {
			response.sendRedirect(URLPattern.REDIRECT_URL_TO_BASIC_LAYOUT);
		}
	}
}
