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
import services.LeMauvaisCoinServices;


@WebServlet("/show")
public class ShowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LeMauvaisCoinServices service;
    public ShowController() {
        super();
        service = new LeMauvaisCoinServices();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("AnnouncementView.jsp").forward(request, response);
			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email = request.getParameter("txtEmail");
			String password = request.getParameter("txtPassword");
			if(service.IsCustomerInfoValid(email, password)) {
				HttpSession session = request.getSession();
				String pseudonyme = service.getCustomerPseudonymeByEmail(email);
				session.setAttribute("pseudonyme",pseudonyme);
				session.setAttribute("idCustomer", service.getCustomerIdByEmail(email) );
				List<Announcement> list =service.getAllAnnoucementByIdCustomer((int)session.getAttribute("idCustomer"));
				session.setAttribute("listAnnoncementConnectedCustomer", list);
				request.getRequestDispatcher("personalspace").forward(request, response);
			}else {
				request.getRequestDispatcher("AnnouncementView.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}

}
