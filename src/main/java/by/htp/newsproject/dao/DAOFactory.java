package by.htp.newsproject.dao;

import by.htp.newsproject.dao.impl.NewsDAOImpl;
import by.htp.newsproject.dao.impl.UserDAOImpl;

public final class DAOFactory {

	private final static DAOFactory instance = new DAOFactory();
	
	private DAOFactory() {
		
	}
	
	private final UserDAO userdao = new UserDAOImpl();
	private final NewsDAO newsdao = new NewsDAOImpl();

	public UserDAO getUserDAO() {
		return userdao;
	}
	public NewsDAO getNewsDAO() {
		return newsdao;
	}


	public static DAOFactory getInstance() {
		return instance;
	}
	
}
