package by.htp.newsproject.service.impl;

import java.util.Comparator;
import java.util.SortedSet;

import by.htp.newsproject.controller.bean.NewsBean;
import by.htp.newsproject.dao.DAOExeption;
import by.htp.newsproject.dao.DAOFactory;
import by.htp.newsproject.dao.NewsDAO;
import by.htp.newsproject.service.NewsService;
import by.htp.newsproject.service.ServiceExeption;
import by.htp.newsproject.service.fullvalidation.FullNewsValidation;
import by.htp.newsproject.service.impl.comparator.NewsByDateComparator;
import by.htp.newsproject.util.BasicNewsValidation;

public class NewsServiceImpl implements NewsService {

	private final static NewsDAO newsDAO = DAOFactory.getInstance().getNewsDAO();	
	private final static Comparator<NewsBean> sortNewsByDate = new NewsByDateComparator();

	@Override
	public ResultOfValidateNews create(NewsBean news) throws ServiceExeption {

		BasicNewsValidation resultOfValidationBasic;
		FullNewsValidation resultOfValidationFull;
		ResultOfValidateNews resultOfValidateNews;

		ResultOfValidateNews.ResultOfValidateNewsBuilder resultOfValidateBuilder = new ResultOfValidateNews.ResultOfValidateNewsBuilder();

		try {
			resultOfValidationBasic = validateNewsBasic(news);
			
			resultOfValidateNews = resultOfValidateBuilder
					.resultValidateBasic(resultOfValidationBasic)
					.build();

			if (resultOfValidationBasic.checkIsAllDataValid()) {

				resultOfValidationFull = validateNewsFull(news);
				
				resultOfValidateNews = resultOfValidateBuilder
						.resultFullValidate(resultOfValidationFull)
						.build();

				if (resultOfValidationFull.checkIsAllDataValid()) {
					
					newsDAO.createNews(news);
					
					resultOfValidateNews = resultOfValidateBuilder
							.checkNewsStatus(true)
							.build();
				} else {			
					resultOfValidateNews = resultOfValidateBuilder
							.checkNewsStatus(false)
							.build();
				}
			}
			return resultOfValidateNews;
			
		} catch (DAOExeption e) {
			throw new ServiceExeption(e);
		}

	}

	@Override
	public NewsBean readOneNews(int NewsID) throws ServiceExeption {

		try {
			return newsDAO.readOneNews(NewsID);
		} catch (DAOExeption e) {
			throw new ServiceExeption(e);
		}
	}

	@Override
	public SortedSet<NewsBean> readAllNewsSortedByDate() throws ServiceExeption {

		try {
			return newsDAO.readAllNews(sortNewsByDate);
		} catch (DAOExeption e) {
			throw new ServiceExeption(e);
		}
	}
	
	@Override
	public SortedSet<NewsBean> readPortionOfNews(int pageIDStart, int pageIDEnd) throws ServiceExeption {
		
		try {
			return newsDAO.readPortionNews(sortNewsByDate, pageIDStart, pageIDEnd);
		} catch (DAOExeption e) {
			throw new ServiceExeption(e);
		}
	}

	@Override
	public ResultOfValidateNews update(NewsBean news) throws ServiceExeption {

		BasicNewsValidation resultOfValidationBasic;
		FullNewsValidation resultOfValidationFull;
		ResultOfValidateNews resultOfValidateNews;

		ResultOfValidateNews.ResultOfValidateNewsBuilder resultOfValidateBuilder = new ResultOfValidateNews.ResultOfValidateNewsBuilder();

		try {
			resultOfValidationBasic = validateNewsBasic(news);
			
			resultOfValidateNews = resultOfValidateBuilder
					.resultValidateBasic(resultOfValidationBasic)
					.build();

			if (resultOfValidationBasic.checkIsAllDataValid()) {

				resultOfValidationFull = validateNewsFull(news);
				
				resultOfValidateNews = resultOfValidateBuilder
						.resultFullValidate(resultOfValidationFull)
						.build();

				if (resultOfValidationFull.checkIsAllDataValid()) {
					
					newsDAO.updateNews(news);
					
					resultOfValidateNews = resultOfValidateBuilder
							.checkNewsStatus(true)
							.build();
				} else {			
					resultOfValidateNews = resultOfValidateBuilder
							.checkNewsStatus(false)
							.build();
				}
			}
			return resultOfValidateNews;			
		
		} catch (DAOExeption e) {
			throw new ServiceExeption(e);
		}
	}

	@Override
	public void deleteChosenNews(String[] newsID) throws ServiceExeption {

		try {
			for (String ID : newsID) {
				newsDAO.deleteOneNews(ID);
			}
		} catch (DAOExeption e) {
			throw new ServiceExeption(e);
		}
	}

	@Override
	public int readNewsID(NewsBean news) throws ServiceExeption {

		try {
			int newsID = newsDAO.readNewsID(news);
			return newsID;
		} catch (DAOExeption e) {
			throw new ServiceExeption(e);
		}
	}

	private FullNewsValidation validateNewsFull(NewsBean news) {

		FullNewsValidation.FullNewsValidationBuilder validator = new FullNewsValidation.FullNewsValidationBuilder();

		FullNewsValidation result = validator
				.isTitleFillSize(news.getTitle())
				.isBriefFillSize(news.getBrief())
				.isNewsContentFillSize(news.getContent())
				.build();

		return result;
	}

	private BasicNewsValidation validateNewsBasic(NewsBean news) {

		BasicNewsValidation.BasicValidationBuilder validator = new BasicNewsValidation.BasicValidationBuilder();

		BasicNewsValidation result = validator
				.isTitleEmptyOrNull(news.getTitle())
				.isBriefEmptyOrNull(news.getBrief())
				.isDateEmptyOrNull(news.getDate())
				.isNewsContentEmptyOrNull(news.getContent())
				.build();

		return result;
	}
}
