package by.htp.newsproject.service;

import by.htp.newsproject.controller.bean.AuthorizationUserBean;
import by.htp.newsproject.controller.bean.NewUserBean;
import by.htp.newsproject.service.impl.SignUpResultTransfer;

public interface UserService {
	
	String authorization (AuthorizationUserBean user) throws ServiceExeption;
	SignUpResultTransfer signUp (NewUserBean user) throws ServiceExeption;
	int takeUserID(AuthorizationUserBean user) throws ServiceExeption;

}
