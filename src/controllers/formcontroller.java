package controllers;

import java.io.IOException;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	   BeanUser user = new BeanUser();
	   BeanUtilities.populateBean(user, request);
	    if (user.isComplete()) {
	      System.out.println("TODO: Fer un insert a la BD");
	    } else {
	     
	    request.setAttribute("user",user);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/form.jsp");
	    if (dispatcher != null) dispatcher.forward(request, response);
	    	
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
