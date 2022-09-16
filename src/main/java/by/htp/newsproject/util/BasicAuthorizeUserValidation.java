package by.htp.newsproject.util;

public class BasicAuthorizeUserValidation {
	private final boolean login;
	private final boolean password;

	private BasicAuthorizeUserValidation(BasicAuthorizeUserValidationBuilder obj) {

		this.login = obj.login;
		this.password = obj.password;
	}

	public boolean isLogin() {
		return login;
	}

	public boolean isPassword() {
		return password;
	}

	public boolean checkIsAllDataValid() {

		if (login && password) {
			return true;
		}
		return false;
	}

	public static class BasicAuthorizeUserValidationBuilder  {

		private boolean login;
		private boolean password;

		public BasicAuthorizeUserValidationBuilder isLoginEmptyOrNull(String UserLogin) {

			if (UserLogin != null) {
				login = !UserLogin.isBlank();
			} else {
				login = false;
			}
			return this;
		}

		public BasicAuthorizeUserValidationBuilder isPasswordEmptyOrNull(String UserPassword) {

			if (UserPassword != null) {
				password = !UserPassword.isBlank();
			} else {
				password = false;
			}
			return this;
		}

	
		public BasicAuthorizeUserValidation build() {

			return new BasicAuthorizeUserValidation(this);
		}
	}
}
