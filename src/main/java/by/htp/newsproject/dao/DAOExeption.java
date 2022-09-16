package by.htp.newsproject.dao;

public class DAOExeption extends Exception {

	private static final long serialVersionUID = -9038204111799704825L;
	
	public DAOExeption() {
		super();
	}
	
	public DAOExeption(String message) {
		super(message);
	}
	
	public DAOExeption(Exception e) {
		super(e);
	}

	public DAOExeption(String message, Exception e) {
		super(message,e);
	}

}
