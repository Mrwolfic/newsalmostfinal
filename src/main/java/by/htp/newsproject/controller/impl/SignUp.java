package by.htp.newsproject.controller.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.newsproject.controller.Command;
import by.htp.newsproject.controller.bean.NewUserBean;
import by.htp.newsproject.controller.bean.SignUpParameter;
import by.htp.newsproject.controller.constant.RequestParam;
import by.htp.newsproject.controller.constant.URLPattern;
import by.htp.newsproject.service.ServiceExeption;
import by.htp.newsproject.service.ServiceFactory;
import by.htp.newsproject.service.impl.SignUpResultTransfer;
import by.htp.newsproject.util.BasicNewUserValidation;
import by.htp.newsproject.util.MethodUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignUp implements Command {
	
	private final static MethodUtil methodUtil = new MethodUtil();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final Logger log = LogManager.getRootLogger();
		
		NewUserBean user = createUser(request);
		
		BasicNewUserValidation userValidationData = validate(user);

		SignUpResultTransfer signUpResult;

		try {
			if (userValidationData.checkIsAllDataValid()) {

				signUpResult = ServiceFactory.getInstance().getUserservice().signUp(user);
			} else {
				
				methodUtil.putNewsListAttribute(request);

				request.setAttribute(RequestParam.KEY_TO_AUTH_OR_SIGN_UP_ATTRIBUTE, RequestParam.VALUE_TO_SIGN_UP_ATTRIBUTE);
				request.setAttribute(RequestParam.KEY_TO_SIGN_UP_ATTRIBUTE_BASIC_ERROR, userValidationData);
				request.setAttribute(RequestParam.KEY_TO_USER_ATTRIBUTE, user);

				RequestDispatcher requestDispatcher = request.getRequestDispatcher(URLPattern.FORWARD_URL_TO_BASIC_LAYOUT);

				if (requestDispatcher != null) {
					requestDispatcher.forward(request, response);
				} else {
					response.sendRedirect(URLPattern.REDIRECT_URL_TO_ERROR_PAGE);
				}
				return;
			}
			
			final String redirectSignUp;
			
			if (signUpResult.isNewUser()) {

				redirectSignUp = URLPattern.REDIRECT_URL_TO_BASIC_LAYOUT + "&" + RequestParam.KEY_TO_AUTH_OR_SIGN_UP_ATTRIBUTE + "=" + RequestParam.VALUE_TO_SIGN_UP_ATTRIBUTE + "&"
						+ RequestParam.KEY_TO_SIGN_UP_ATTRIBUTE_SUCCESS_MESSAGE + "=" + RequestParam.VALUE_TO_SIGN_UP_ATTRIBUTE_SUCCESS_MESSAGE;

				response.sendRedirect(redirectSignUp);

			} else {
				
				methodUtil.putNewsListAttribute(request);
				
				if (signUpResult.getResultValidateBasic().checkIsAllDataValid()) {
					
					request.setAttribute(RequestParam.KEY_TO_SIGN_UP_ATTRIBUTE_FULL_ERROR, signUpResult.getResultFullValidate());
				} else {
					
					request.setAttribute(RequestParam.KEY_TO_SIGN_UP_ATTRIBUTE_BASIC_ERROR, signUpResult.getResultValidateBasic());
				}

				request.setAttribute(RequestParam.KEY_TO_AUTH_OR_SIGN_UP_ATTRIBUTE, RequestParam.VALUE_TO_SIGN_UP_ATTRIBUTE);
				request.setAttribute(RequestParam.KEY_TO_USER_ATTRIBUTE, user);

				RequestDispatcher requestDispatcher = request.getRequestDispatcher(URLPattern.FORWARD_URL_TO_BASIC_LAYOUT);

				if (requestDispatcher != null) {
					requestDispatcher.forward(request, response);
				} else {
					response.sendRedirect(URLPattern.REDIRECT_URL_TO_ERROR_PAGE);
				}
			}
		} catch (ServiceExeption e) {

			log.log(Level.ERROR, "Sign Up goes wrong", e);
			response.sendRedirect(URLPattern.REDIRECT_URL_TO_ERROR_PAGE);
		}
	}

	private NewUserBean createUser(HttpServletRequest request) {

		NewUserBean user = new NewUserBean();

		user.setLogin(request.getParameter(SignUpParameter.LOGIN_PARAM));
		user.setPassword(request.getParameter(SignUpParameter.PASSWORD_PARAM));
		user.setEmail(request.getParameter(SignUpParameter.EMAIL_PARAM));	
		user.setGender(request.getParameter(SignUpParameter.GENDER_PARAM));
		user.setAdditionalinfo(request.getParameter(SignUpParameter.ADDITIONAL_INFO_PARAM));
		
		LocalDate dateOfBirth;
		
		try {		
			if (request.getParameter(SignUpParameter.DATE_PARAM) != null) {
				
				dateOfBirth = methodUtil.parseStringToLocalDate(request.getParameter(SignUpParameter.DATE_PARAM));
				user.setDateofbirth(dateOfBirth);
			} else {			
				user.setDateofbirth(null);;
			}
			
		} catch (DateTimeParseException e) {	
			user.setDateofbirth(null);
		}
		
		return user;
	}

	private BasicNewUserValidation validate(NewUserBean user) {

		BasicNewUserValidation.BasicValidationBuilder basicvalidation = new BasicNewUserValidation.BasicValidationBuilder();
		BasicNewUserValidation result = basicvalidation
				.isLoginEmptyOrNull(user.getLogin())	
				.isPasswordEmptyOrNull(user.getPassword())
				.isEmailEmptyOrNull(user.getEmail())
				.isDateofbirthEmptyOrNull(user.getDateofbirth())
				.isGenderEmptyOrNull(user.getGender())
				.build();

		return result;
	}
}
