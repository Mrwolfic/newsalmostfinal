package by.htp.newsproject.controller.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.SortedSet;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.newsproject.controller.Command;
import by.htp.newsproject.controller.bean.NewsBean;
import by.htp.newsproject.controller.constant.RequestParam;
import by.htp.newsproject.controller.constant.URLPattern;
import by.htp.newsproject.service.NewsService;
import by.htp.newsproject.service.ServiceExeption;
import by.htp.newsproject.service.ServiceFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToPortionNewsPage implements Command {

	private final static NewsService newsService = ServiceFactory.getInstance().getNewsservice();
	private final static Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final int newsOnOnePageAmountDefaultValue = 5;
		int firstPage = 1;
		int pageIDStart;
		int amountOfPreviousPages = (Integer.parseInt(request.getParameter("PageID")) - 1)
				* newsOnOnePageAmountDefaultValue;

		try {
			SortedSet<NewsBean> newsSet = newsService.readAllNewsSortedByDate();

			Iterator<NewsBean> iterator = newsSet.iterator();

			NewsBean[] portionArray = new NewsBean[newsOnOnePageAmountDefaultValue];

			if (Integer.parseInt(request.getParameter("PageID")) == firstPage) {
				pageIDStart = firstPage;
			} else {
				pageIDStart = amountOfPreviousPages + 1;
			}

			int pageIDEnd = pageIDStart + newsOnOnePageAmountDefaultValue - 1;

			if (Integer.parseInt(request.getParameter("PageID")) == firstPage) {
				for (int i = pageIDStart; i < pageIDEnd + 1 && iterator.hasNext(); i++) {
					portionArray[i - 1] = iterator.next();
				}
			} else {
				for (int k = 0; k < amountOfPreviousPages; k++) {
					iterator.next();
				}
				for (int i = 0; i < newsOnOnePageAmountDefaultValue && iterator.hasNext(); i++) {
					portionArray[i] = iterator.next();
				}
			}

			if (newsSet.size() != 0) {
				request.setAttribute(RequestParam.KEY_TO_NEWS_SET_ATTRIBUTE, portionArray);
			}

			RequestDispatcher requestDispatcher = request.getRequestDispatcher(URLPattern.FORWARD_URL_TO_BASIC_LAYOUT);

			if (requestDispatcher != null) {
				requestDispatcher.forward(request, response);
			} else {
				response.sendRedirect(URLPattern.REDIRECT_URL_TO_ERROR_PAGE);
			}

		} catch (ServiceExeption e) {
			log.log(Level.ERROR, "Something wrong pagination", e);
			response.sendRedirect(URLPattern.REDIRECT_URL_TO_ERROR_PAGE);
		}
	}
}
