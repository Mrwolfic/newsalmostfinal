package by.htp.newsproject.dao.impl;

public class DBNewsTableFields {

	private DBNewsTableFields() {

	}
	
	public final static String SELECT_ALL_NEWS_SQL = "SELECT * FROM news";
	public final static String NEWS_TITLE = "n_title";
	public final static String NEWS_BREIF = "n_breif";
	public final static String NEWS_CONTENT = "n_content";
	public final static String NEWS_DATE = "n_dateofcreation";
	public final static String NEWS_USER_ID = "n_user_u_id";
	public final static String NEWS_ID = "n_id";

}
