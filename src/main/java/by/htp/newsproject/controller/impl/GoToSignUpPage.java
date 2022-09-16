package by.htp.newsproject.controller.impl;

import java.io.IOException;

import by.htp.newsproject.controller.Command;
import by.htp.newsproject.controller.constant.RequestParam;
import by.htp.newsproject.controller.constant.URLPattern;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToSignUpPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String redirectURL = URLPattern.REDIRECT_URL_TO_BASIC_LAYOUT + "&" + RequestParam.KEY_TO_AUTH_OR_SIGN_UP_ATTRIBUTE + "="
				+ RequestParam.VALUE_TO_SIGN_UP_ATTRIBUTE;
		
		response.sendRedirect(redirectURL);
	}
}
