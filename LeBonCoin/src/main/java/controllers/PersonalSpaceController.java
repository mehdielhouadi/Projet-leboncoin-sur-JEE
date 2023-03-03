package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.LeMauvaisCoinServices;


@WebServlet("/personalspace")
public class PersonalSpaceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LeMauvaisCoinServices service;

    public PersonalSpaceController() {
        super();
        service = new LeMauvaisCoinServices();
    }
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession();
		request.getRequestDispatcher("PersonalSpaceView.jsp").forward(request, response);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	

}
