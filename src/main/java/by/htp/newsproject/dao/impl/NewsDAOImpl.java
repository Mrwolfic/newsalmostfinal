package by.htp.newsproject.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.newsproject.controller.bean.NewsBean;
import by.htp.newsproject.dao.DAOExeption;
import by.htp.newsproject.dao.NewsDAO;
import by.htp.newsproject.dao.connectionpool.ConnectionPool;
import by.htp.newsproject.dao.connectionpool.ConnectionPoolException;

public class NewsDAOImpl implements NewsDAO {
	
	final Logger log = LogManager.getRootLogger();

	private final static String ADD_ONE_NEWS_SQL = "INSERT INTO news(n_title,n_dateofcreation,n_breif,n_content,n_user_u_id) VALUES(?,?,?,?,?)";
	@Override
	public void createNews(NewsBean news) throws DAOExeption {
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_ONE_NEWS_SQL)) {

			Timestamp dateofcreation = convertLocalDateToTimestamp(news);
			
			preparedStatement.setString(1, news.getTitle());
			preparedStatement.setTimestamp(2, dateofcreation);
			preparedStatement.setString(3, news.getBrief());
			preparedStatement.setString(4, news.getContent());
			preparedStatement.setInt(5, news.getUserID());
			preparedStatement.executeUpdate();

		} catch (ConnectionPoolException | SQLException | ParseException e) {
			throw new DAOExeption(e);
		}
	}

	private final static String READ_ONE_NEWS_SQL = "SELECT n_title,n_dateofcreation,n_breif,n_content,n_user_u_id FROM news WHERE n_id=? ";
	@Override
	public NewsBean readOneNews(int NewsID) throws DAOExeption {

		try (Connection connection = ConnectionPool.getInstance().takeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(READ_ONE_NEWS_SQL);) {

			preparedStatement.setInt(1, NewsID);

			ResultSet result = preparedStatement.executeQuery();
			result.next();

			NewsBean news = new NewsBean();
			LocalDateTime date = result.getTimestamp(DBNewsTableFields.NEWS_DATE).toLocalDateTime();
			
			news.setTitle(result.getString(DBNewsTableFields.NEWS_TITLE));
			news.setBrief(result.getString(DBNewsTableFields.NEWS_BREIF));
			news.setContent(result.getString(DBNewsTableFields.NEWS_CONTENT));
			news.setDate(LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), date.getHour(), date.getMinute()));
			news.setNewsID(NewsID);
			
				
			return news;

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOExeption(e);
		}
	}

	private final static String SELECT_ALL_NEWS_SQL = "SELECT * FROM news WHERE n_status='exist'";
	@Override
	public SortedSet<NewsBean> readAllNews(Comparator<NewsBean> comparator) throws DAOExeption {

		SortedSet<NewsBean> newsSet = new TreeSet<>(comparator);

		try (Connection connection = ConnectionPool.getInstance().takeConnection();
				Statement statement = connection.createStatement()) {

			
			ResultSet result = statement.executeQuery(SELECT_ALL_NEWS_SQL);

			while (result.next()) {
				NewsBean news = new NewsBean();

				news.setTitle(result.getString(DBNewsTableFields.NEWS_TITLE));
				news.setBrief(result.getString(DBNewsTableFields.NEWS_BREIF));
				news.setContent(result.getString(DBNewsTableFields.NEWS_CONTENT));
				news.setDate(result.getTimestamp(DBNewsTableFields.NEWS_DATE).toLocalDateTime());
				news.setUserID(result.getInt(DBNewsTableFields.NEWS_USER_ID));
				news.setNewsID(result.getInt(DBNewsTableFields.NEWS_ID));

				newsSet.add(news);
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOExeption(e);
		}
		
		return newsSet;
	}
	
	private final static String SELECT_PORTION_OF_NEWS_SQL = "SELECT * FROM news WHERE n_status='exist' AND n_id>? AND n_id<?";
	@Override
	public SortedSet<NewsBean> readPortionNews(Comparator<NewsBean> comparator, int pageIDStart, int pageIDEnd) throws DAOExeption {
		
		SortedSet<NewsBean> newsSet = new TreeSet<>(comparator);

		try (Connection connection = ConnectionPool.getInstance().takeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PORTION_OF_NEWS_SQL);) {

			preparedStatement.setInt(1, pageIDStart);
			preparedStatement.setInt(2, pageIDEnd);
			
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				NewsBean news = new NewsBean();

				news.setTitle(result.getString(DBNewsTableFields.NEWS_TITLE));
				news.setBrief(result.getString(DBNewsTableFields.NEWS_BREIF));
				news.setContent(result.getString(DBNewsTableFields.NEWS_CONTENT));
				news.setDate(result.getTimestamp(DBNewsTableFields.NEWS_DATE).toLocalDateTime());
				news.setUserID(result.getInt(DBNewsTableFields.NEWS_USER_ID));
				news.setNewsID(result.getInt(DBNewsTableFields.NEWS_ID));

				newsSet.add(news);
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOExeption(e);
		}
		
		return newsSet;
	}

	private final static String UPDATE_ONE_NEWS = "UPDATE news SET n_title=?,n_dateofcreation=?,n_breif=?,n_content=? WHERE n_id = ?";
	@Override
	public void updateNews(NewsBean news) throws DAOExeption {
		
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ONE_NEWS);) {
	
			Timestamp dateofcreation = convertLocalDateToTimestamp(news);
			
			preparedStatement.setString(1, news.getTitle());
			preparedStatement.setTimestamp(2, dateofcreation);
			preparedStatement.setString(3, news.getBrief());
			preparedStatement.setString(4, news.getContent());
			preparedStatement.setInt(5, news.getNewsID());
			
			preparedStatement.executeUpdate();
			
			news = readOneNews(news.getNewsID());

		} catch (ConnectionPoolException | SQLException | ParseException e) {
			throw new DAOExeption(e);
		}	
	}
	
	private final static String DELETE_ONE_NEWS_SQL = "UPDATE news SET n_status='delete' WHERE n_id = ?";
	@Override
	public void deleteOneNews(String newsID) throws DAOExeption {
		
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ONE_NEWS_SQL);) {
			
			preparedStatement.setInt(1, Integer.parseInt(newsID));		
			preparedStatement.executeUpdate();
			
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOExeption(e);
		}	
	}
	
	private final static String READ_NEWS_ID_SQL = "SELECT n_id FROM news WHERE n_content=?";
	@Override
	public int readNewsID(NewsBean news) throws DAOExeption {
		
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(READ_NEWS_ID_SQL);) {
			
			preparedStatement.setString(1, news.getContent());	
			
			ResultSet result = preparedStatement.executeQuery();
			result.next();
			
			int newsID = result.getInt(1);
			
			return newsID;
			
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOExeption(e);
		}			
	}
	
	private Timestamp convertLocalDateToTimestamp(NewsBean news) throws ParseException {
		
		LocalDateTime date = news.getDate();

		Timestamp timestamp = Timestamp.valueOf(date);
		
		return timestamp;
	}
}
