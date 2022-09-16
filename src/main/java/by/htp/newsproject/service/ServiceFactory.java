package by.htp.newsproject.service;

import by.htp.newsproject.service.impl.NewsServiceImpl;
import by.htp.newsproject.service.impl.UserServiceImpl;

public final class ServiceFactory {
	
	private final static ServiceFactory instance = new ServiceFactory();
	
	private ServiceFactory() {
		
	}

	private final UserService userservice = new UserServiceImpl();
	private final NewsService newsservice = new NewsServiceImpl();
	
	public UserService getUserservice() {
		return userservice;
	}
	
	public static ServiceFactory getInstance() {
		return instance;
	}
	public NewsService getNewsservice() {
		return newsservice;
	}
	
	
	
	
	

}
