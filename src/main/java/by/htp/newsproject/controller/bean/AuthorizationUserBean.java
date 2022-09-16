package by.htp.newsproject.controller.bean;

import java.io.Serializable;
import java.util.Objects;

public class AuthorizationUserBean implements Serializable  {

	private static final long serialVersionUID = -4013388428604941289L;
	
	private String login;
	private String password;
	
	public AuthorizationUserBean() {
		
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(login, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthorizationUserBean other = (AuthorizationUserBean) obj;
		return Objects.equals(login, other.login) && Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return "AuthorizationUserInfo [login=" + login + ", password=" + password + "]";
	}
	

}
