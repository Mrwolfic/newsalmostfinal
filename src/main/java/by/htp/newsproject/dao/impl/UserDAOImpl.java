package by.htp.newsproject.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;

import by.htp.newsproject.controller.bean.AuthorizationUserBean;
import by.htp.newsproject.controller.bean.NewUserBean;
import by.htp.newsproject.dao.DAOExeption;
import by.htp.newsproject.dao.UserDAO;
import by.htp.newsproject.dao.connectionpool.ConnectionPool;
import by.htp.newsproject.dao.connectionpool.ConnectionPoolException;

public class UserDAOImpl implements UserDAO {

	private final static String CHECK_IF_USER_ID_WITH_GIVEN_LOGIN_EXIST_SQL = "SELECT u_id FROM user WHERE u_login=?";
	@Override
	public boolean isUserIDwithCurrentLoginExist(String userLogin) throws DAOExeption {

		try (Connection connection = ConnectionPool.getInstance().takeConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(CHECK_IF_USER_ID_WITH_GIVEN_LOGIN_EXIST_SQL)) {

			preparedStatement.setString(1, userLogin);
			ResultSet result = preparedStatement.executeQuery();

			return result.next();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOExeption(e);
		}
	}

	private final static String SELECT_PASSWORD_WITH_GIVEN_LOGIN_SQL = "SELECT u_password FROM user WHERE u_login=?";
	@Override
	public String readPasswordWithGivenLogin(String userLogin) throws DAOExeption {

		String password;
		final String valueForNonExistingPassword = "Not Exist";

		try (Connection connection = ConnectionPool.getInstance().takeConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(SELECT_PASSWORD_WITH_GIVEN_LOGIN_SQL)) {

			preparedStatement.setString(1, userLogin);
			ResultSet result = preparedStatement.executeQuery();

			if (result.next()) {
				password = result.getString(1);
			} else {
				password = valueForNonExistingPassword;
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOExeption(e);
		}
		return password;
	}

	private final static String CHECK_IF_USER_ID_WITH_GIVEN_EMAIL_EXIST_SQL = "SELECT u_id FROM user WHERE u_email=?";
	@Override
	public boolean isUserIDwithCurrentEmailExist(String userEmail) throws DAOExeption {

		try (Connection connection = ConnectionPool.getInstance().takeConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(CHECK_IF_USER_ID_WITH_GIVEN_EMAIL_EXIST_SQL)) {

			preparedStatement.setString(1, userEmail);
			ResultSet result = preparedStatement.executeQuery();

			return result.next();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOExeption(e);
		}
	}

	private final static String ROLE_ADMIN = "admin";
	private final static String ROLE_USER = "user";
	private final static String FIND_USER_ROLE_SQL = "SELECT u_roles_r_id FROM user WHERE u_login=?";
	@Override
	public String getRole(AuthorizationUserBean user) throws DAOExeption {

		try (Connection connection = ConnectionPool.getInstance().takeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_ROLE_SQL)) {

			preparedStatement.setString(1, user.getLogin());
			ResultSet result = preparedStatement.executeQuery();

			result.next();
			int roleID = result.getInt(1);

			if (roleID == 1) {
				return ROLE_ADMIN;
			}
			return ROLE_USER;

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOExeption(e);
		}
	}

	private final static String FIND_USER_ID_SQL = "SELECT u_id FROM user WHERE u_login=?";
	@Override
	public int getUserID(AuthorizationUserBean user) throws DAOExeption {

		try (Connection connection = ConnectionPool.getInstance().takeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_ID_SQL)) {

			preparedStatement.setString(1, user.getLogin());
			ResultSet result = preparedStatement.executeQuery();

			result.next();
			int userID = result.getInt(1);

			return userID;

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOExeption(e);
		}
	}

	private static final String ADD_NEW_USER_SQL = "INSERT INTO user(u_login,u_password,u_email,u_dateofbirth,u_info,u_gender) VALUES(?,?,?,?,?,?)";
	@Override
	public boolean signUp(NewUserBean user) throws DAOExeption {

		try (Connection connection = ConnectionPool.getInstance().takeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_USER_SQL)) { 
			
			Timestamp timeStampDate = convertLocalDateToTimestamp(user);

			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setTimestamp(4, timeStampDate);
			preparedStatement.setString(5, user.getAdditionalinfo());
			preparedStatement.setString(6, user.getGender());

			preparedStatement.executeUpdate();

			return true;

		} catch (ConnectionPoolException | SQLException | ParseException e) {
			throw new DAOExeption(e);
		}
	}
	
	private Timestamp convertLocalDateToTimestamp(NewUserBean user) throws ParseException {
		
			LocalDate date = user.getDateofbirth();

			Timestamp timestamp = Timestamp.valueOf(date.atStartOfDay());
		
			return timestamp;
	}
}
