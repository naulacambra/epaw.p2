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
	static final int KEY = 0;
	static final int VALUE = 1;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public formcontroller() {
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
		BeanUser user = new BeanUser();
		BeanUtilities.populateBean(user, request);
		if (user.isComplete()) {
			System.out.println("TODO: Fer un insert a la BD");
		} else {

			request.setAttribute("user", user);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/form.jsp");
			if (dispatcher != null)
				dispatcher.forward(request, response);

		}
	}

	/**
	 * @throws IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		ArrayList<String[]> objectToReturn = new ArrayList<String[]>();
		switch (request.getParameter("action")) {
		case "checkUsername":
			String[] data = new String[2];
			data[KEY] = "'success'";
			data[VALUE] = "false";
			objectToReturn.add(data);
			break;
		default:
		}
		String stringToReturn = arrayToJson( new ArrayList<String[]>() );
		response.getWriter().write( stringToReturn );
	}

	private String arrayToJson(ArrayList<String[]> array) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for(int i=0; i < array.size(); ++i){
			sb.append(array.get(i)[KEY] + ":" + array.get(i)[VALUE]);
		}
		sb.append("}");
		return sb.toString();
	}
}
