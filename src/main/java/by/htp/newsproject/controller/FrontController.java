package by.htp.newsproject.controller;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.newsproject.controller.constant.RequestParam;
import by.htp.newsproject.controller.constant.SessionAttribute;
import by.htp.newsproject.dao.connectionpool.ConnectionPool;
import by.htp.newsproject.dao.connectionpool.ConnectionPoolException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class FrontController extends HttpServlet {
	
	private final static Logger log = LogManager.getRootLogger();
	private static final long serialVersionUID = 1L;
	private final CommandFactory factory = new CommandFactory();

	public FrontController() {
		super();
	}
	@Override
	public void init() {
		try {		
			ConnectionPool instance = ConnectionPool.getInstance();
			instance.initPoolData();
						
		} catch (ConnectionPoolException e) {			
			log.log(Level.ERROR, "ConnectionPool broken", e);
		}	
	}
	
	@Override
	public void destroy() {
		
		try {
			ConnectionPool instance = ConnectionPool.getInstance();
			instance.clearConnectionQueue();
		} catch (ConnectionPoolException e) {
			log.log(Level.ERROR, "ConnectionPool broken", e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		executeRequest(request, response);
		saveRequestURL(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		executeRequest(request, response);
		saveRequestURL(request, response);
	}

	private void executeRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final String contentType = "text/html";

		response.setContentType(contentType);
		
		String name = request.getParameter(RequestParam.KEY_TO_FRONT_CONTROLLER_ATTRIBUTE);
		Command command = factory.getCommands(name);
		command.execute(request, response);
	}

	private void saveRequestURL(HttpServletRequest request, HttpServletResponse response) {

		final String ruLocale = "ru";
		final String enLocale = "en";

		String fullQuery = request.getQueryString();

		if (fullQuery != null && !fullQuery.endsWith(ruLocale) && !fullQuery.endsWith(enLocale)) {

			request.getSession(true).setAttribute(SessionAttribute.KEY_TO_LAST_REQUEST_URL_SESSION_ATTRIBUTE,
					fullQuery);
		}
	}
}
