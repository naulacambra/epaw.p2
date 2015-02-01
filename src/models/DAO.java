package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
	private Connection connection;
	private Statement statement;

	public DAO() throws Exception {
		String user = "consulter";
		String password = "qwerty";
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager
				.getConnection("jdbc:mysql://localhost/twittsire?user=" + user
						+ "&password=" + password);
		statement = connection.createStatement();
	}

	// execute queries
	public ResultSet executeSQL(String query) throws SQLException {
		return statement.executeQuery(query);
	}
	
	// update queries
	public void executeSQLUpdate(String query) throws SQLException {
		statement.executeUpdate(query);
	}

	// TODO: code for updates for Assignments 2, 3 and 4.
	public void disconnectBD() throws SQLException {
		statement.close();
		connection.close();
	}
}