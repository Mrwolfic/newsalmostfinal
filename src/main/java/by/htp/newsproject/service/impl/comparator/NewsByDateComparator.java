package by.htp.newsproject.service.impl.comparator;

import java.util.Comparator;

import by.htp.newsproject.controller.bean.NewsBean;

public class NewsByDateComparator implements Comparator<NewsBean> {

	@Override
	public int compare(NewsBean o1, NewsBean o2) {
	
		return o2.getDate().compareTo(o1.getDate());
	}
}
