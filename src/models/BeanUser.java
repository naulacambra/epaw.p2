package models;

public class BeanUser {

	private String fullName = "";
	private String username = "";
	private String mail = "";
	private String pwd = "";
	private int[] error = { 0, 0 };

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
	
	static public boolean usernameExists( String username ){
		return true;
	}
}
