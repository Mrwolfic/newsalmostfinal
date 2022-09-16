package by.htp.newsproject.util;

import java.time.LocalDate;

public class BasicNewUserValidation {

	private final boolean login;
	private final boolean password;
	private final boolean email;
	private final boolean dateofbirth;
	private final boolean gender;

	private BasicNewUserValidation(BasicValidationBuilder obj) {

		this.login = obj.login;
		this.password = obj.password;
		this.email = obj.email;
		this.dateofbirth = obj.dateofbirth;
		this.gender = obj.gender;
	}

	public boolean isLogin() {
		return login;
	}


	public boolean isPassword() {
		return password;
	}


	public boolean isEmail() {
		return email;
	}


	public boolean isDateofbirth() {
		return dateofbirth;
	}


	public boolean isGender() {
		return gender;
	}


	public boolean checkIsAllDataValid() {

		if (login && password && email && dateofbirth && gender) {
			return true;
		}
		return false;
	}

	public static class BasicValidationBuilder {

		private boolean login;
		private boolean password;
		private boolean email;
		private boolean dateofbirth;
		private boolean gender;

		public BasicValidationBuilder isLoginEmptyOrNull(String newUserLogin) {

			if (newUserLogin != null) {
				login = !newUserLogin.isBlank();
			} else {
				login = false;
			}
			return this;
		}

		public BasicValidationBuilder isPasswordEmptyOrNull(String newUserPassword) {

			if (newUserPassword != null) {
				password = !newUserPassword.isBlank();
			} else {
				password = false;
			}
			return this;
		}

		public BasicValidationBuilder isEmailEmptyOrNull(String newUserEmail) {

			if (newUserEmail != null) {
				email = !newUserEmail.isBlank();
			} else {
				email = false;
			}
			return this;
		}

		public BasicValidationBuilder isDateofbirthEmptyOrNull(LocalDate newUserDateofbirth) {

			if (newUserDateofbirth != null) {
				dateofbirth = true;
			} else {
				dateofbirth = false;
			}
			return this;
		}

		public BasicValidationBuilder isGenderEmptyOrNull(String newUserGender) {

			if (newUserGender != null) {
				gender = !newUserGender.isBlank();
			} else {
				gender = false;
			}
			return this;
		}
		
		public BasicNewUserValidation build() {

			return new BasicNewUserValidation(this);
		}

	}
}
