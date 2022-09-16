package by.htp.newsproject.controller.impl;

import java.io.IOException;

import by.htp.newsproject.controller.Command;
import by.htp.newsproject.controller.constant.SessionAttribute;
import by.htp.newsproject.controller.constant.URLPattern;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignOut implements Command {
		
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession(true).removeAttribute(SessionAttribute.KEY_TO_ROLE_SESSION_ATTRIBUTE);
		request.getSession().removeAttribute(SessionAttribute.KEY_TO_USER_ID_SESSION_ATTRIBUTE);
		
		response.sendRedirect(URLPattern.URL_TO_STARTING_PAGE);	
	}
}
