package by.htp.newsproject.controller.impl;

import java.io.IOException;

import by.htp.newsproject.controller.AuthorizeBeanParameter;
import by.htp.newsproject.controller.Command;
import by.htp.newsproject.controller.bean.AuthorizationUserBean;
import by.htp.newsproject.controller.constant.RequestParam;
import by.htp.newsproject.controller.constant.SessionAttribute;
import by.htp.newsproject.controller.constant.URLPattern;
import by.htp.newsproject.service.ServiceExeption;
import by.htp.newsproject.service.ServiceFactory;
import by.htp.newsproject.util.BasicAuthorizeUserValidation;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Authorize implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final Logger log = LogManager.getRootLogger();

		AuthorizationUserBean user = createUser(request);

		boolean validateResult = basicValidate(user);

		String role;
		int userID;

		try {
			if (validateResult) {

				role = ServiceFactory.getInstance().getUserservice().authorization(user);

			} else {
				
				final String redirectURLtwo = URLPattern.REDIRECT_URL_TO_BASIC_LAYOUT + "&" + RequestParam.KEY_TO_AUTH_OR_SIGN_UP_ATTRIBUTE
						+ "=" + RequestParam.VALUE_TO_AUTHORIZATION_ATTRIBUTE + "&"
						+ RequestParam.KEY_TO_AUTENTIFICATION_ERROR_MESSAGE + "=" + RequestParam.VALUE_TO_AUTENTIFICATION_ERROR_MESSAGE;

				response.sendRedirect(redirectURLtwo);
				return;
			}

			if (!RequestParam.ROLE_FOR_NONAUTHORIZED_USERS.equals(role)) {

				userID = ServiceFactory.getInstance().getUserservice().takeUserID(user);

				request.getSession(true).setAttribute(SessionAttribute.KEY_TO_ROLE_SESSION_ATTRIBUTE, role);
				request.getSession().setAttribute(SessionAttribute.KEY_TO_USER_ID_SESSION_ATTRIBUTE, userID);

				response.sendRedirect(URLPattern.REDIRECT_URL_TO_BASIC_LAYOUT);
			} else {

				final String redirectURL = URLPattern.REDIRECT_URL_TO_BASIC_LAYOUT + "&" + RequestParam.KEY_TO_AUTH_OR_SIGN_UP_ATTRIBUTE
						+ "=" + RequestParam.VALUE_TO_AUTHORIZATION_ATTRIBUTE + "&" + RequestParam.KEY_TO_AUTENTIFICATION_ERROR_MESSAGE + "="
						+ RequestParam.VALUE_TO_AUTENTIFICATION_ERROR_MESSAGE;

				response.sendRedirect(redirectURL);
			}

		} catch (ServiceExeption e) {

			log.log(Level.ERROR, "Authorization goes wrong", e);
			response.sendRedirect(URLPattern.REDIRECT_URL_TO_ERROR_PAGE);
		}
	}

	private AuthorizationUserBean createUser(HttpServletRequest request) {

		AuthorizationUserBean user = new AuthorizationUserBean();

		user.setLogin(request.getParameter(AuthorizeBeanParameter.LOGIN_PARAM));
		user.setPassword(request.getParameter(AuthorizeBeanParameter.PASSWORD_PARAM));

		return user;
	}

	private boolean basicValidate(AuthorizationUserBean user) {

		BasicAuthorizeUserValidation.BasicAuthorizeUserValidationBuilder basicValidation = new BasicAuthorizeUserValidation.BasicAuthorizeUserValidationBuilder();

		BasicAuthorizeUserValidation authorizeUserValidationBuilder = basicValidation
				.isLoginEmptyOrNull(user.getLogin())
				.isPasswordEmptyOrNull(user.getPassword())
				.build();

		if (authorizeUserValidationBuilder.checkIsAllDataValid()) {
			return true;
		}
		return false;
	}

}
