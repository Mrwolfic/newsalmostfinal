package by.htp.newsproject.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import by.htp.newsproject.controller.bean.NewsBean;
import by.htp.newsproject.controller.constant.RequestParam;
import by.htp.newsproject.controller.constant.SessionAttribute;
import by.htp.newsproject.service.NewsService;
import by.htp.newsproject.service.ServiceExeption;
import by.htp.newsproject.service.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;

public class MethodUtil {

	private final static NewsService newsService = ServiceFactory.getInstance().getNewsservice();

	public void putNewsListAttribute(HttpServletRequest request) throws ServiceExeption {

		final int newsOnOnePageAmountDefaultValue = 5;

		SortedSet<NewsBean> newsSet = newsService.readAllNewsSortedByDate();

		Iterator<NewsBean> iterator = newsSet.iterator();

		NewsBean[] portionArray = new NewsBean[newsOnOnePageAmountDefaultValue];

		for (int i = 0; i < newsOnOnePageAmountDefaultValue; i++) {
			portionArray[i] = iterator.next();
		}

		if (newsSet.size() != 0) {
			request.setAttribute(RequestParam.KEY_TO_NEWS_SET_ATTRIBUTE, portionArray);
		}

		final List<Integer> pagesArray = new ArrayList<>();

		for (int i = 1; i <= newsSet.size() / newsOnOnePageAmountDefaultValue + 1; i++) {
			pagesArray.add(i);
		}

		request.getSession().setAttribute(SessionAttribute.KEY_TO_PAGE_AMOUNT_SESSION_ATTRIBUTE, pagesArray);

	}

	public LocalDateTime parseStringToLocalDateTime(String date) throws DateTimeParseException {

		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
		int nanoSec = LocalDateTime.now().getNano();
		int seconds = LocalDateTime.now().getSecond();

		return localDateTime.plusSeconds(seconds).plusNanos(nanoSec);
	}

	public LocalDate parseStringToLocalDate(String date) throws DateTimeParseException {

		final String datePattern = "yyyy-MM-dd";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
		LocalDate localDate = LocalDate.parse(date, formatter);

		return localDate;
	}
}
