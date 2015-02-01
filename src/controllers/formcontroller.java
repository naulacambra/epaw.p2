package controllers;

import java.io.IOException;
import java.util.ArrayList;

import models.BeanUser;
import utils.BeanUtilities;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class formcontroller
 */
@WebServlet("/formcontroller")
public class formcontroller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public formcontroller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws ServletException
	 * @throws IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		BeanUser user = new BeanUser();
		BeanUtilities.populateBean(user, request);
		if (user.isComplete()) {
			user.saveUser();
			BeanUser[] users = BeanUser.getUsers();
			request.setAttribute("users", users);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/list.jsp");
			if (dispatcher != null)
				dispatcher.forward(request, response);
		}else{
			System.out.println(user.getName());
			System.out.println(user.getSurname());
			System.out.println(user.getUsername());
			System.out.println(user.getMail());
			System.out.println(user.getPwd());
			System.out.println("L'usuari no està complet");
		}
	}
}
