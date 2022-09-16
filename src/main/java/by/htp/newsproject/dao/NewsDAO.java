package by.htp.newsproject.dao;

import java.util.Comparator;
import java.util.SortedSet;

import by.htp.newsproject.controller.bean.NewsBean;

public interface NewsDAO {

	void createNews(NewsBean news) throws DAOExeption;
	NewsBean readOneNews(int NewsID) throws DAOExeption;
	void updateNews(NewsBean news) throws DAOExeption;
	void deleteOneNews(String newsID) throws DAOExeption;
	SortedSet<NewsBean> readAllNews(Comparator<NewsBean> comparator) throws DAOExeption;
	SortedSet<NewsBean> readPortionNews(Comparator<NewsBean> comparator, int pageIDStart, int pageIDEnd) throws DAOExeption;
	int readNewsID (NewsBean news) throws DAOExeption;
}
