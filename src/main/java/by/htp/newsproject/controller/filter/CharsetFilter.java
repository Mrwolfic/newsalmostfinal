package by.htp.newsproject.controller.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class CharsetFilter implements Filter {

	private String encoding;
	private ServletContext context;
	
	private final static String PARAMETER_NAME = "characterEncoding";
	@Override
	public void init(FilterConfig filterConfig) {	
		encoding = filterConfig.getInitParameter(PARAMETER_NAME);
		context = filterConfig.getServletContext();		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {	
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		
		context.log("Charset was set.");
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
