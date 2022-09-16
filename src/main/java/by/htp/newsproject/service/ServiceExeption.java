package by.htp.newsproject.service;

public class ServiceExeption extends Exception {

	private static final long serialVersionUID = 1052244625197939225L;
	
	public ServiceExeption() {
		super();
	}
	
	public ServiceExeption(String message) {
		super(message);
	}
	
	public ServiceExeption(Exception e) {
		super(e);
	}

	public ServiceExeption(String message, Exception e) {
		super(message,e);
	}
}
