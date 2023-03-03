package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Announcement;
import entities.Customer;
import services.LeMauvaisCoinServices;

@WebServlet("/registerin")
public class RegisterInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LeMauvaisCoinServices service;
	

    public RegisterInController() {
    	super();
    	service = new LeMauvaisCoinServices();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("RegisterInView.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pseudonyme = request.getParameter("txtPseudonyme");
		String firstName = request.getParameter("txtFirstName");
		String lastName = request.getParameter("txtLastName");
		String email = request.getParameter("txtEmail");
		String password = request.getParameter("txtPassword");
		String passwordConfirmation = request.getParameter("txtPasswordConfirmation");
		
		if(password.equals(passwordConfirmation)) {
			try {
				Customer cust = new Customer(-1, pseudonyme, firstName, lastName, email, password);
				service.addCustomer(cust);
				List<Announcement> list = service.getAllAnnoucementByIdCustomer(cust.getId());
				HttpSession session = request.getSession();
				session.setAttribute("pseudonyme",pseudonyme);
				session.setAttribute("id", cust.getId());
				session.setAttribute("listAnnoncementConnectedCustomer", list);
				request.getRequestDispatcher("personalspace").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				request.getRequestDispatcher("show").forward(request, response);
			}
		}else {
			request.getRequestDispatcher("RegisterInView.jsp").forward(request, response);
		}
		
		
	}

}
