package by.htp.newsproject.controller.constant;

public class RequestParam {
	
	private RequestParam() {
		
	}
	
	public final static String SERVLET_PATTERN_NAME = "FrontController?";
	
	public final static String KEY_TO_FRONT_CONTROLLER_ATTRIBUTE = "check";
	
	public final static String KEY_TO_AUTH_OR_SIGN_UP_ATTRIBUTE = "auth_or_sign";
	public final static String VALUE_TO_AUTHORIZATION_ATTRIBUTE = "authorize";
	public final static String VALUE_TO_SIGN_UP_ATTRIBUTE = "signup";
	
	public final static String KEY_TO_AUTENTIFICATION_ERROR_MESSAGE = "AuthenticationError";
	public final static String VALUE_TO_AUTENTIFICATION_ERROR_MESSAGE = "Invalid login or password!";
	
	public final static String KEY_TO_NEWS_BASIC_ERROR_MESSAGE = "NewsBasicError";
	public final static String KEY_TO_NEWS_FULL_ERROR_MESSAGE = "NewsFullError";
	
	public final static String CHECKBOX_PARAM_NAME = "NewsCheckBox";
	
	public final static String KEY_TO_NEWS_ATTRIBUTE = "News";
	public final static String VALUE_TO_VIEW_NEWS_ATTRIBUTE = "ViewNews";
	public final static String VALUE_TO_ADD_NEWS_ATTRIBUTE = "AddNews";
	public final static String VALUE_TO_EDIT_NEWS_ATTRIBUTE = "EditNews";

	public final static String KEY_NEWS_ID = "NewsID";
	
	public final static String KEY_TO_NEWS_SET_ATTRIBUTE = "NewsList";

	public final static String KEY_TO_VIEW_NEWS_OBJECT = "NewsToView";
	public final static String KEY_TO_EDIT_NEWS_OBJECT = "NewsToEdit";
	public final static String KEY_TO_ADD_NEWS_OBJECT = "NewsToAdd";
	
	public final static String KEY_TO_SIGN_UP_ATTRIBUTE_BASIC_ERROR = "BasicSignUpError";
	public final static String KEY_TO_SIGN_UP_ATTRIBUTE_FULL_ERROR = "FullSignUpError";
	
	public final static String KEY_TO_NEWS_ATTRIBUTE_BASIC_ERROR = "BasicNewsError";
	public final static String KEY_TO_NEWS_ATTRIBUTE_FULL_ERROR = "FullNewsError";
	
	public final static String KEY_TO_SIGN_UP_ATTRIBUTE_SUCCESS_MESSAGE = "SignUpDone";
	public final static String VALUE_TO_SIGN_UP_ATTRIBUTE_SUCCESS_MESSAGE = "You're succesfully signed up!";
	
	public final static String KEY_TO_USER_ATTRIBUTE = "user";
	
	public final static String ROLE_FOR_NONAUTHORIZED_USERS = "guest";

}
