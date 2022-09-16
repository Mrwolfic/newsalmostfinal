package by.htp.newsproject.service.fullvalidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.htp.newsproject.dao.DAOExeption;
import by.htp.newsproject.dao.DAOFactory;
import by.htp.newsproject.dao.UserDAO;

public class FullNewUserValidation {

	private final boolean loginValid;
	private final boolean loginAlreadyExist;
	private final boolean password;
	private final boolean emailValid;
	private final boolean emailAlreadyExist;
	private final boolean gender;

	private FullNewUserValidation(FullUserValidationBuilder obj) {

		this.loginValid = obj.loginValid;
		this.loginAlreadyExist = obj.loginAlreadyExist;
		this.password = obj.password;
		this.emailValid = obj.emailValid;
		this.emailAlreadyExist = obj.emailAlreadyExist;
		this.gender = obj.gender;
	}

	public boolean isLoginValid() {
		return loginValid;
	}

	public boolean isLoginAlreadyExist() {
		return loginAlreadyExist;
	}

	public boolean isPassword() {
		return password;
	}

	public boolean isEmailValid() {
		return emailValid;
	}

	public boolean isEmailAlreadyExist() {
		return emailAlreadyExist;
	}

	public boolean isGender() {
		return gender;
	}

	public boolean isAllDataValid() {

		if (loginValid && loginAlreadyExist && password && emailValid && emailAlreadyExist && gender) {
			return true;
		}
		return false;
	}

	public static class FullUserValidationBuilder {

		private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

		private boolean loginValid;
		private boolean loginAlreadyExist;
		private boolean password;
		private boolean emailValid;
		private boolean emailAlreadyExist;
		private boolean gender;

		public FullUserValidationBuilder isLoginValid(String newUserLogin) {

			String regex = "^[а-яА-ЯёЁa-zA-Z0-9]{3,15}$";

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(newUserLogin);

			loginValid = matcher.matches();

			return this;
		}

		public FullUserValidationBuilder isLoginAlreadyExist(String newUserLogin) throws DAOExeption {

			loginAlreadyExist = !userDAO.isUserIDwithCurrentLoginExist(newUserLogin);

			return this;
		}

		public FullUserValidationBuilder isPasswordValid(String newUserPassword) {

			String regex = "^.{3,12}$";

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(newUserPassword);

			password = matcher.matches();

			return this;
		}

		public FullUserValidationBuilder isEmailValid(String newUserEmail) {

			String regex = "^(.+)@(\\S+)$";

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(newUserEmail);

			emailValid = matcher.matches();

			return this;
		}
		
		public FullUserValidationBuilder isEmailAlreadyExist(String newUserEmail) throws DAOExeption {

			emailAlreadyExist = !userDAO.isUserIDwithCurrentEmailExist(newUserEmail);

			return this;
		}

		public FullUserValidationBuilder isGenderValid(String newUserGender) {

			if ("male".equals(newUserGender) || "female".equals(newUserGender)) {

				gender = true;
			} else {
				gender = false;
			}

			return this;
		}

	
		public FullNewUserValidation build() {

			return new FullNewUserValidation(this);
		}
	}

}
