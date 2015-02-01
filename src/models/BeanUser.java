package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.DAO;
import utils.Encryption;

public class BeanUser {

	private String name = "";
	private String surname = "";
	private String username = "";
	private String mail = "";
	private String pwd = "";
	private int[] error = { 0, 0 };
	private static DAO database = null;

	/* Getters */
	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getUsername() {
		return username;
	}

	public String getMail() {
		return mail;
	}

	public String getPwd() {
		return pwd;
	}

	public int[] getError() {
		return error;
	}

	/* Setters */
	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setPwd(String pwd) {
		this.pwd = Encryption.MD5(pwd);
	}

	/* Logic Functions */

	public boolean isComplete() {
		return (hasValue(getUsername()) && hasValue(getMail()));
	}

	private boolean hasValue(String val) {
		return ((val != null) && (!val.equals("")));
	}

	public String toString() {
		return this.name + " " + this.surname + " " + this.username + " "
				+ this.mail + " " + this.pwd;
	}

	static public boolean usernameExists(String username) {
		if (database == null)
			try {
				database = new DAO();
			} catch (Exception e) {
				e.printStackTrace();
				return true;
			}

		try {
			ResultSet result = database
					.executeSQL("SELECT count(*) as count FROM Users WHERE username = '"
							+ username + "'");
			if (result.first())
				if (result.getInt("count") > 0)
					return true;
				else
					return false;
			else
				return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return true;
		}
	}

	static public boolean mailExists(String mail) {
		if (database == null)
			try {
				database = new DAO();
			} catch (Exception e) {
				e.printStackTrace();
				return true;
			}

		try {
			ResultSet result = database
					.executeSQL("SELECT count(*) as count FROM Users WHERE mail = '"
							+ mail + "'");
			if (result.first())
				if (result.getInt("count") > 0)
					return true;
				else
					return false;
			else
				return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return true;
		}
	}

	public static ArrayList<BeanUser> getUsers() {
		ArrayList<BeanUser> users = new ArrayList<BeanUser>();
		if (database == null)
			try {
				database = new DAO();
			} catch (Exception e) {
				e.printStackTrace();
			}
		try {
			ResultSet result = database.executeSelectSQL("SELECT * FROM Users");

			while (result.next()) {
				BeanUser tempUser = new BeanUser();
				tempUser.setName(result.getString("name"));
				tempUser.setSurname(result.getString("surname"));
				tempUser.setUsername(result.getString("username"));
				tempUser.setMail(result.getString("mail"));
				tempUser.setPwd(result.getString("pwd"));
				users.add(tempUser);
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return users;
		}
	}

	public void saveUser() {
		if (database == null)
			try {
				database = new DAO();
			} catch (Exception e) {
				e.printStackTrace();
			}

		try {
			database.executeInsertSQL("INSERT INTO Users (`name`, surname, username, mail, pwd) "
					+ "VALUES ('"
					+ name
					+ "', '"
					+ surname
					+ "', '"
					+ username
					+ "', '" + mail + "', '" + pwd + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
