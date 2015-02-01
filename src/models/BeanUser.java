package models;

import java.sql.ResultSet;

import utils.DAO;

public class BeanUser {

	private String fullName = "";
	private String username = "";
	private String mail = "";
	private String pwd = "";
	private int[] error = { 0, 0 };
	private static DAO database = null;

	/* Getters */
	public String getFullName() {
		return fullName;
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
	public void setFullName(String fullName) {

		// Consultar si existeix un usuari igual a la BD
		error[0] = 1;

	}

	public void setUsername(String username) {

		// Consultar si existeix un usuari igual a la BD
		error[0] = 1;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/* Logic Functions */

	public boolean isComplete() {
		return (hasValue(getUsername()) && hasValue(getMail()));
	}

	private boolean hasValue(String val) {
		return ((val != null) && (!val.equals("")));
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
}
