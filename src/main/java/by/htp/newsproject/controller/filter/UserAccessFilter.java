package by.htp.newsproject.controller.filter;

import java.io.IOException;

import by.htp.newsproject.controller.constant.URLPattern;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserAccessFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		CommandNameToCheckAccess[] commandNameArray = CommandNameToCheckAccess.values();
		String query = httpRequest.getQueryString();

		for (CommandNameToCheckAccess command : commandNameArray) {
			if (query != null && query.contains(command.name())) {
				if (!httpRequest.isRequestedSessionIdValid()) {

					httpResponse.sendRedirect(URLPattern.REDIRECT_URL_TO_BASIC_LAYOUT);
				}
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
