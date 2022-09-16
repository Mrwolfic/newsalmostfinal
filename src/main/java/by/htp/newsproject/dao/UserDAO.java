package by.htp.newsproject.dao;

import by.htp.newsproject.controller.bean.AuthorizationUserBean;
import by.htp.newsproject.controller.bean.NewUserBean;

public interface UserDAO {

	boolean signUp(NewUserBean user) throws DAOExeption;
	String getRole(AuthorizationUserBean user) throws DAOExeption;
	int getUserID(AuthorizationUserBean user) throws DAOExeption;
	boolean isUserIDwithCurrentLoginExist(String userLogin) throws DAOExeption;
	boolean isUserIDwithCurrentEmailExist(String userEmail) throws DAOExeption;
	String readPasswordWithGivenLogin(String userLogin) throws DAOExeption;
}
