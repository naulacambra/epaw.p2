package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.BeanUser;
import utils.JSON;

/**
 * Servlet implementation class ajaxcontroller
 */
@WebServlet("/ajaxcontroller")
public class ajaxcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final int KEY = 0;
	static final int VALUE = 1;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ajaxcontroller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JSON result = new JSON();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		switch (request.getParameter("action")) {
		case "checkUsername":
			result.addPair("success", true);
			result.addPair("exists", BeanUser.usernameExists("naulacambra"));
			break;
		case "checkMail":
			result.addPair("success", false);
			break;
		default:
		}

		response.getWriter().write(result.toString());
	}
}
