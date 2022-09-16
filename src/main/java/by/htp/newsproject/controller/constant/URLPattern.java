package by.htp.newsproject.controller.constant;

public final class URLPattern {
	
	private URLPattern() {
	}
	
	public final static String REDIRECT_URL_TO_VIEW_ONE_NEWS_PAGE = "FrontController?check=GO_TO_VIEW_ONE_NEWS_PAGE";
	public final static String REDIRECT_URL_TO_BASIC_LAYOUT = "FrontController?check=GO_TO_BASIC_LAYOUT";
	public final static String REDIRECT_URL_TO_ERROR_PAGE = "FrontController?check=GO_TO_ERROR_PAGE";
	public final static String FORWARD_URL_TO_ERROR_PAGE = "ErrorPage.jsp";
	public final static String FORWARD_URL_TO_BASIC_LAYOUT = "/WEB-INF/pages/BasicLayout.jsp";
	public final static String URL_TO_STARTING_PAGE = "Index.jsp";
}
