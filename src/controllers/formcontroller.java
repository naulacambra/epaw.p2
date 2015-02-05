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
		//Si rebem una petici� GET la processem com si fos POST
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
		//Creem un usuari per poder gestionar la petici�
		BeanUser user = new BeanUser();
		//Intentem omplir-lo amb les dades que ens arriben en la petici�
		BeanUtilities.populateBean(user, request);
		if (user.isComplete()) {
			//Si l'usuari t� totes les dades el guardem
			user.saveUser();
			//Generem un ArrayList amb els usuaris guardats
			ArrayList<BeanUser> users = BeanUser.getUsers();
			//Enviem aquest ArrayList amb la resposta al JSP
			request.setAttribute("users", users);
			//Cridem al JSP list.jsp per mostrar la llista d'usuaris
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/list.jsp");
			if (dispatcher != null)
				dispatcher.forward(request, response);
		} else {
			//Si l'usuari no est� complet, el retornem al formulari inicial
			request.setAttribute("user", user);
			//Cridem al JSP form.jsp per tornar a mostrar el formulari
			//o mostrar-lo per primera vegada
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/form.jsp");
			if (dispatcher != null)
				dispatcher.forward(request, response);
		}
	}
}
