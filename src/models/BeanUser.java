package models;

public class BeanUser {

	private String user = "";
	private String mail = "";
	private int[] error = {0,0}; 
	
	/* Getters */
	public String getUser(){
		return user;
	}
	
	public String getMail() {
		return mail;
	}
	
	public int[] getError() {
		return error;
	}
	
	/*Setters*/
	public void setUser(String user){
		
		//Consultar si existeix un usuari igual a la BD
		error[0] = 1;
		
	}
	
	public void setMail(String mail){
		this.mail = mail;
	}
	
	/* Logic Functions */
	
	
	
	public boolean isComplete() {
	    return(hasValue(getUser()) &&
	           hasValue(getMail()) );
	}
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
}
