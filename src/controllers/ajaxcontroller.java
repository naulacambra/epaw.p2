package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import models.BeanUser;
import utils.BeanUtilities;
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
//		response.setContentType("application/json");
//		response.setCharacterEncoding("UTF-8");

//		ArrayList<String[]> objectToReturn = new ArrayList<String[]>();
//		switch (request.getParameter("action")) {
//		case "checkUsername":
//			String[] data = new String[2];
//			data[KEY] = "'success'";
//			data[VALUE] = "false";
//			objectToReturn.add(data);
//			break;
//		default:
//		}
//		String stringToReturn = arrayToJson(objectToReturn);
		JSON result = new JSON();
		result.addPair("success", true);
		
		response.getWriter().write(result.toString());
	}
}
