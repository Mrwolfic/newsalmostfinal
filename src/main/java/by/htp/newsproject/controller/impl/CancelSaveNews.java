package by.htp.newsproject.controller.impl;

import java.io.IOException;

import by.htp.newsproject.controller.Command;
import by.htp.newsproject.controller.constant.URLPattern;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CancelSaveNews implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.sendRedirect(URLPattern.REDIRECT_URL_TO_BASIC_LAYOUT);
	}
}
