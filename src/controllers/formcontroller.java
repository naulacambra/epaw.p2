package controllers;

import java.io.IOException;
import java.sql.SQLException;

import models.BeanUser;
import models.DAO;
import utils.BeanUtilities;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.mysql.jdbc.ResultSet;

/**
 * Servlet implementation class formcontroller
 */
public class formcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int ERROR_FULLNAME = 0;
	private static final int ERROR_USERNAME = 1;
	private static final int ERROR_MAIL = 2;
	private static final int ERROR_PWD = 3;
	private static final int ERROR_PWDCHECK = 4;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public formcontroller() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BeanUser user = new BeanUser();
		BeanUtilities.populateBean(user, request);

		System.out.println("TODO:" + user.getFullName());
		System.out.println("TODO:" + user.getMail());

		if (user.getFullName().isEmpty())
			user.setError(ERROR_FULLNAME);
		if (user.getUsername().isEmpty())
			user.setError(ERROR_USERNAME);
		if (user.getMail().isEmpty())
			user.setError(ERROR_MAIL);
		if (user.getPwd().isEmpty())
			user.setError(ERROR_PWD);
		if (!user.getPwd().equals(user.getPwd_check()) || user.getPwd_check().isEmpty())
			user.setError(ERROR_PWDCHECK);

		
		  for(int i : user.getError()){ System.out.println("- " + i); }
		 

		if (user.isComplete() && isNewUser(user) ) {
			System.out.println("Fer un insert a la BD: " + user.getUsername() + " " + user.getMail());
			insertUser(user);
			user = new BeanUser();
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/form.jsp");
			if (dispatcher != null)
				dispatcher.forward(request, response);
		} else {
			System.out.println("NO: "  + user.getUsername() + " " + user.getMail());
			user = (!user.isOnCreate()) ? new BeanUser() : user; // to hide form errors when
																	// starting the servlet
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/form.jsp");
			if (dispatcher != null)
				dispatcher.forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST REQUEST");
		if(request.getParameter("action") != null)
		{
			switch (request.getParameter("action")) {
			case "validateUsername":
				System.out.println("he rebut la peticio USERNAME: " + request.getParameter("data"));
				if( isValidUsername(request.getParameter("data")) )
				{
				    String text = "valid";
				    response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
				    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
				    response.getWriter().write(text);       // Write response body.
				}
				break;
			case "validateMail":
				System.out.println("he rebut la peticio MAIL: " + request.getParameter("data"));
				if( isValidMail(request.getParameter("data")) )
				{
				    String text = "valid";
				    response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
				    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
				    response.getWriter().write(text);       // Write response body.
				}
				break;
			default:
			}
		}
		else if(request.getParameter("action") == null)
			doGet(request, response);
	}

	/**
	 * Check if the DB already has same mail address or username field related
	 * to some user. Used as a backend second check.
	 * 
	 * @param user populated bean
	 * @return true when new user
	 */
	private boolean isNewUser(BeanUser user) {
		if(user.getError()[ERROR_USERNAME] != 0 || user.getError()[ERROR_MAIL] != 0)
			return false;	// si els camps estan buits sortim de la funció
		
		DAO db = null;
		ResultSet isUsernameRepeated = null; // number of coincidences
		ResultSet isMailRepeated = null; // if it's different from 0 -> user already in DB
		
		try {
			db = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			isUsernameRepeated = (ResultSet) db
					.executeSQL("SELECT COUNT(*) AS isUsernameRepeated FROM twittsire.users WHERE username = '"
							+ user.getUsername() + "'");
			if (isUsernameRepeated.next()) {
				int repetitions = Integer.parseInt(isUsernameRepeated
						.getString("isUsernameRepeated"));
				if (repetitions != 0)
					user.setError(ERROR_USERNAME);
			}

			isMailRepeated = (ResultSet) db
					.executeSQL("SELECT COUNT(*) AS isMailRepeated FROM twittsire.users WHERE mail = '"
							+ user.getMail() + "'");
			if (isMailRepeated.next()) {
				int repetitions = Integer.parseInt(isMailRepeated
						.getString("isMailRepeated"));
				if (repetitions != 0)
					user.setError(ERROR_MAIL);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			db.disconnectBD(); // close the DB connection
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (!user.hasErrors())
			return true;
		return false;
	}

	/**
	 * Check if the DB already has the same username Used in AJAX connection.
	 * 
	 * @param user populated bean
	 * @return true when new user
	 */
	private boolean isValidUsername(String username) {
		try {
			DAO db = new DAO();
			ResultSet isUsernameRepeated = (ResultSet) db
					.executeSQL("SELECT COUNT(*) AS isUsernameRepeated FROM twittsire.users WHERE username = '"
							+ username + "'");
			if (isUsernameRepeated.next()) {
				int repetitions = Integer.parseInt(isUsernameRepeated
						.getString("isUsernameRepeated"));
				if (repetitions != 0) {
					db.disconnectBD();
					return false;
				} else {
					db.disconnectBD();
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Check if the DB already has the same mail Used in AJAX connection.
	 * 
	 * @param user populated bean
	 * @return true when new user
	 */
	private boolean isValidMail(String mail) {
		try {
			DAO db = new DAO();
			ResultSet isMailRepeated = (ResultSet) db
					.executeSQL("SELECT COUNT(*) AS isMailRepeated FROM twittsire.users WHERE mail = '"
							+ mail + "'");
			if (isMailRepeated.next()) {
				int repetitions = Integer.parseInt(isMailRepeated
						.getString("isMailRepeated"));
				if (repetitions != 0) {
					db.disconnectBD();
					return false;
				} else {
					db.disconnectBD();
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	private void insertUser(BeanUser user) {
		DAO db = null;
		try {
			db = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			db.executeSQLUpdate("INSERT INTO twittsire.users (fullName, username, mail, pwd) VALUES ('"
					+ user.getFullName()
					+ "','"
					+ user.getUsername()
					+ "','"
					+ user.getMail() + "','" + user.getPwd() + "')");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			db.disconnectBD(); // close the DB connection
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
