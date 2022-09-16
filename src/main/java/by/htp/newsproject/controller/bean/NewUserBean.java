package by.htp.newsproject.controller.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class NewUserBean implements Serializable {

	private static final long serialVersionUID = -3289636590418812870L;
	
	private String login;
	private String password;
	private String email;
	private LocalDate dateofbirth;
	private String gender;
	private String additionalinfo;
	
	public NewUserBean() {
		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public LocalDate getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(LocalDate dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAdditionalinfo() {
		return additionalinfo;
	}

	public void setAdditionalinfo(String additionalinfo) {
		this.additionalinfo = additionalinfo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(additionalinfo, dateofbirth, email, gender, login, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewUserBean other = (NewUserBean) obj;
		return Objects.equals(additionalinfo, other.additionalinfo) && Objects.equals(dateofbirth, other.dateofbirth)
				&& Objects.equals(email, other.email) && Objects.equals(gender, other.gender)
				&& Objects.equals(login, other.login) && Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return "NewUserBean [login=" + login + ", password=" + password + ", email=" + email + ", dateofbirth="
				+ dateofbirth + ", gender=" + gender + ", additionalinfo=" + additionalinfo + "]";
	}
	
	
	
}
