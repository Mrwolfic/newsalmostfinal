package by.htp.newsproject.service;

import java.util.SortedSet;

import by.htp.newsproject.controller.bean.NewsBean;
import by.htp.newsproject.service.impl.ResultOfValidateNews;

public interface NewsService {
	
	ResultOfValidateNews create(NewsBean news) throws ServiceExeption;
	ResultOfValidateNews update(NewsBean news) throws ServiceExeption;
	void deleteChosenNews(String[] newsID) throws ServiceExeption;
	
	NewsBean readOneNews(int NewsID) throws ServiceExeption;
	SortedSet<NewsBean> readAllNewsSortedByDate() throws ServiceExeption;
	SortedSet<NewsBean> readPortionOfNews(int pageIDStart, int pageIDEnd) throws ServiceExeption;
	int readNewsID(NewsBean news) throws ServiceExeption;

}
