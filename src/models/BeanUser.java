package models;

public class BeanUser {

	private String fullName = "";
	private String username = "";
	private String mail = "";
	private String password = "";
	private int[] error = { 0, 0 };

	/* Getters */
	public String getFullName() {
		return fullName;
	}

	public String getUser() {
		return username;
	}

	public String getMail() {
		return mail;
	}

	public String getPassword() {
		return password;
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
	
	public void setPassword(String password) {
		this.password = password;
	}

	/* Logic Functions */

	public boolean isComplete() {
		return (hasValue(getUser()) && hasValue(getMail()));
	}

	private boolean hasValue(String val) {
		return ((val != null) && (!val.equals("")));
	}
}
