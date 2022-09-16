package by.htp.newsproject.service.fullvalidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;

import by.htp.newsproject.controller.bean.AuthorizationUserBean;
import by.htp.newsproject.dao.DAOExeption;
import by.htp.newsproject.dao.DAOFactory;
import by.htp.newsproject.dao.UserDAO;

public class FullAuthorizeUserValidation {

	private final boolean loginValid;
	private final boolean loginExist;
	private final boolean passwordValid;
	private final boolean passwordExist;

	private FullAuthorizeUserValidation(FullAuthorizeUserValidationBuilder obj) {

		this.loginValid = obj.loginValid;
		this.loginExist = obj.loginExist;
		this.passwordValid = obj.passwordValid;
		this.passwordExist = obj.passwordExist;
	}

	public boolean isLoginValid() {
		return loginValid;
	}

	public boolean isLoginExist() {
		return loginExist;
	}

	public boolean isPasswordValid() {
		return passwordValid;
	}

	public boolean isPasswordExist() {
		return passwordExist;
	}

	public boolean isAllDataValid() {

		if (loginValid && loginExist && passwordValid && passwordExist) {
			return true;
		}
		return false;
	}

	public static class FullAuthorizeUserValidationBuilder {

		private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

		private boolean loginValid;
		private boolean loginExist;
		private boolean passwordValid;
		private boolean passwordExist;

		public FullAuthorizeUserValidationBuilder isLoginValid(String authorizeUserLogin) {

			String regex = "^[а-яА-ЯёЁa-zA-Z0-9]{3,15}$";

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(authorizeUserLogin);

			loginValid = matcher.matches();

			return this;
		}

		public FullAuthorizeUserValidationBuilder isLoginExist(String authorizeUserLogin) throws DAOExeption {

			loginExist = userDAO.isUserIDwithCurrentLoginExist(authorizeUserLogin);
			return this;
		}

		public FullAuthorizeUserValidationBuilder isPasswordValid(String authorizeUserPassword) {

			String regex = "^.{3,12}$";

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(authorizeUserPassword);

			passwordValid = matcher.matches();

			return this;
		}

		private final static String VALUE_FOR_NONEXIST_PASSWORD = "Not Exist";

		public FullAuthorizeUserValidationBuilder isPasswordExist(AuthorizationUserBean user) throws DAOExeption {

			String password;
			String salt;
			String hashedAuthorizationPassword;

			password = userDAO.readPasswordWithGivenLogin(user.getLogin());

			if (VALUE_FOR_NONEXIST_PASSWORD.equals(password)) {

				passwordExist = false;
			} else {

				salt = password.substring(0, 29);
				hashedAuthorizationPassword = BCrypt.hashpw(user.getPassword(), salt);
				
				if (password.equals(hashedAuthorizationPassword)) {
					passwordExist = true;
				} else {
					
					passwordExist = false;
				}
			}
			return this;
		}

		public FullAuthorizeUserValidation build() {

			return new FullAuthorizeUserValidation(this);
		}
	}
}
