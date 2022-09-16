package by.htp.newsproject.controller.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.newsproject.controller.Command;
import by.htp.newsproject.controller.bean.NewsBean;
import by.htp.newsproject.controller.bean.NewsParameter;
import by.htp.newsproject.controller.constant.RequestParam;
import by.htp.newsproject.controller.constant.URLPattern;
import by.htp.newsproject.service.NewsService;
import by.htp.newsproject.service.ServiceExeption;
import by.htp.newsproject.service.ServiceFactory;
import by.htp.newsproject.service.impl.ResultOfValidateNews;
import by.htp.newsproject.util.BasicNewsValidation;
import by.htp.newsproject.util.MethodUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EditNews implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final Logger log = LogManager.getRootLogger();

		NewsService newsService = ServiceFactory.getInstance().getNewsservice();

		NewsBean news;

		try {
			news = createNews(request);

			BasicNewsValidation resultOfBasicValidation = validateNewsBasic(news);

			if (resultOfBasicValidation.checkIsAllDataValid()) {

				ResultOfValidateNews resultOfValidateNews = newsService.update(news);

				if (resultOfValidateNews.newsStatus) {

					news = newsService.readOneNews(news.getNewsID());
					
					StringBuilder redirectURL = new StringBuilder(URLPattern.REDIRECT_URL_TO_VIEW_ONE_NEWS_PAGE)
							.append("&")
							.append(RequestParam.KEY_NEWS_ID)
							.append("=")
							.append(news.getNewsID());
					
					response.sendRedirect(redirectURL.toString());
					
				} else {
					request.setAttribute(RequestParam.KEY_TO_NEWS_ATTRIBUTE, RequestParam.VALUE_TO_EDIT_NEWS_ATTRIBUTE);
					request.setAttribute(RequestParam.KEY_TO_EDIT_NEWS_OBJECT, news);
					request.setAttribute(RequestParam.KEY_TO_NEWS_FULL_ERROR_MESSAGE, resultOfValidateNews);
					request.setAttribute(RequestParam.KEY_NEWS_ID, news.getNewsID());

					RequestDispatcher requestDispatcher = request
							.getRequestDispatcher(URLPattern.FORWARD_URL_TO_BASIC_LAYOUT);

					if (requestDispatcher != null) {
						requestDispatcher.forward(request, response);
					} else {
						response.sendRedirect(URLPattern.REDIRECT_URL_TO_ERROR_PAGE);
					}
				}

			} else {

				request.setAttribute(RequestParam.KEY_TO_NEWS_ATTRIBUTE, RequestParam.VALUE_TO_EDIT_NEWS_ATTRIBUTE);
				request.setAttribute(RequestParam.KEY_TO_EDIT_NEWS_OBJECT, news);
				request.setAttribute(RequestParam.KEY_TO_NEWS_BASIC_ERROR_MESSAGE, resultOfBasicValidation);
				request.setAttribute(RequestParam.KEY_NEWS_ID, news.getNewsID());

				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher(URLPattern.FORWARD_URL_TO_BASIC_LAYOUT);

				if (requestDispatcher != null) {
					requestDispatcher.forward(request, response);
				} else {
					response.sendRedirect(URLPattern.REDIRECT_URL_TO_ERROR_PAGE);
				}
			}
		} catch (ServiceExeption e) {
			log.log(Level.ERROR, "Something wrong with updating news", e);
			response.sendRedirect(URLPattern.REDIRECT_URL_TO_ERROR_PAGE);
		}
	}
	
	private final static MethodUtil methodUtil = new MethodUtil();

	private NewsBean createNews(HttpServletRequest request) {

		NewsBean news = new NewsBean();
		
		news.setTitle(request.getParameter(NewsParameter.TITLE_PARAM_NAME));
		news.setBrief(request.getParameter(NewsParameter.BRIEF_PARAM_NAME));
		news.setContent(request.getParameter(NewsParameter.CONTENT_PARAM_NAME));
		news.setUserID((int) request.getSession().getAttribute(NewsParameter.USER_ID));
		news.setNewsID(Integer.parseInt(request.getParameter(NewsParameter.NEWS_ID)));

		LocalDateTime localDate;

		try {

			if (request.getParameter(NewsParameter.DATE_PARAM_NAME) != null) {
				
				localDate = methodUtil.parseStringToLocalDateTime(request.getParameter(NewsParameter.DATE_PARAM_NAME));
				news.setDate(localDate);
			} else {
				news.setDate(null);
			}

		} catch (DateTimeParseException e) {
			news.setDate(null);
		}
		return news;

	}

	private BasicNewsValidation validateNewsBasic(NewsBean news) {

		BasicNewsValidation.BasicValidationBuilder validator = new BasicNewsValidation.BasicValidationBuilder();

		BasicNewsValidation result = validator.
				isTitleEmptyOrNull(news.getTitle())
				.isBriefEmptyOrNull(news.getBrief())
				.isDateEmptyOrNull(news.getDate())
				.isNewsContentEmptyOrNull(news.getContent())
				.build();

		return result;
	}
}
