package by.htp.newsproject.service.impl;

import org.mindrot.jbcrypt.BCrypt;

import by.htp.newsproject.controller.bean.AuthorizationUserBean;
import by.htp.newsproject.controller.bean.NewUserBean;
import by.htp.newsproject.controller.constant.RequestParam;
import by.htp.newsproject.dao.DAOExeption;
import by.htp.newsproject.dao.DAOFactory;
import by.htp.newsproject.dao.UserDAO;
import by.htp.newsproject.service.ServiceExeption;
import by.htp.newsproject.service.UserService;
import by.htp.newsproject.service.fullvalidation.FullAuthorizeUserValidation;
import by.htp.newsproject.service.fullvalidation.FullNewUserValidation;
import by.htp.newsproject.util.BasicAuthorizeUserValidation;
import by.htp.newsproject.util.BasicNewUserValidation;

public class UserServiceImpl implements UserService {

	private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

	@Override
	public String authorization(AuthorizationUserBean user) throws ServiceExeption {

		boolean validateResult;

		try {
			validateResult = fullValidate(user);

			if (validateResult) {
				
				return userDAO.getRole(user);

			} else {
				return RequestParam.ROLE_FOR_NONAUTHORIZED_USERS;
			}

		} catch (DAOExeption e) {
			throw new ServiceExeption(e);
		}
	}

	@Override
	public SignUpResultTransfer signUp(NewUserBean user) throws ServiceExeption {

		BasicNewUserValidation resultValidateBasic;
		FullNewUserValidation resultFullValidate;
		boolean signUpResult;
		
	    SignUpResultTransfer.SignUpResultTransferBuilder resultBulder = new SignUpResultTransfer.SignUpResultTransferBuilder();
		SignUpResultTransfer result;

		try {
			resultValidateBasic = basicValidate(user);
			
			result = resultBulder
					.resultValidateBasic(resultValidateBasic)
					.build();
			
			if (resultValidateBasic.checkIsAllDataValid()) {

				resultFullValidate = fullValidate(user);
				
				result = resultBulder
						.resultFullValidate(resultFullValidate)
						.build();

				if (resultFullValidate.isAllDataValid()) {

					hashPasswordWithSalt(user);
					signUpResult = userDAO.signUp(user);

				} else {
					signUpResult = false;
				}
			} else {
				signUpResult = false;
				resultFullValidate = fullValidate(user);
			}

			
				result = resultBulder
					.newUser(signUpResult)
					.build();

			return result;

		} catch (DAOExeption e) {
			throw new ServiceExeption(e);
		}
	}

	@Override
	public int takeUserID(AuthorizationUserBean user) throws ServiceExeption {

		try {
			return userDAO.getUserID(user);

		} catch (DAOExeption e) {
			throw new ServiceExeption(e);
		}
	}

	private boolean fullValidate(AuthorizationUserBean user) throws DAOExeption {

		if (basicValidate(user)) {

			FullAuthorizeUserValidation.FullAuthorizeUserValidationBuilder FullAuthorizeUserValidationBuilder = new FullAuthorizeUserValidation.FullAuthorizeUserValidationBuilder();

			FullAuthorizeUserValidation FullAuthorizeUserValidation = FullAuthorizeUserValidationBuilder
					.isLoginValid(user.getLogin())
					.isLoginExist(user.getLogin())
					.isPasswordValid(user.getPassword())
					.isPasswordExist(user)
					.build();
			
			return FullAuthorizeUserValidation.isAllDataValid();
			
		} else {
			return false;
		}

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

	private BasicNewUserValidation basicValidate(NewUserBean user) {

		BasicNewUserValidation.BasicValidationBuilder basicValidation = new BasicNewUserValidation.BasicValidationBuilder();
		
		BasicNewUserValidation basicresult = basicValidation
				.isLoginEmptyOrNull(user.getLogin())
				.isPasswordEmptyOrNull(user.getPassword())
				.isEmailEmptyOrNull(user.getEmail())
				.isDateofbirthEmptyOrNull(user.getDateofbirth())
				.isGenderEmptyOrNull(user.getGender())
				.build();

		return basicresult;
	}

	private FullNewUserValidation fullValidate(NewUserBean user) throws DAOExeption {

		FullNewUserValidation.FullUserValidationBuilder fullvalidation = new FullNewUserValidation.FullUserValidationBuilder();

		FullNewUserValidation fullresult = fullvalidation
				.isLoginValid(user.getLogin())
				.isLoginAlreadyExist(user.getLogin())
				.isPasswordValid(user.getPassword())
				.isEmailValid(user.getEmail())
				.isEmailAlreadyExist(user.getEmail())
				.isGenderValid(user.getGender())
				.build();

		return fullresult;
	}

	private void hashPasswordWithSalt(NewUserBean user) {

		String password;
		String salt;
		String saltedHashPassword;

		password = user.getPassword();
		salt = BCrypt.gensalt();
		saltedHashPassword = BCrypt.hashpw(password, salt);

		user.setPassword(saltedHashPassword);
	}
}
