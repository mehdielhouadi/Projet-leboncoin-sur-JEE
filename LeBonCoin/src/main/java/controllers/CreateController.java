package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Announcement;
import services.LeMauvaisCoinServices;


@WebServlet("/create")
public class CreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LeMauvaisCoinServices service;

    public CreateController() {
        super();
        service = new LeMauvaisCoinServices();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				request.getRequestDispatcher("CreateAnnouncementView.jsp").forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idCustomer = (int) request.getSession().getAttribute("idCustomer");
		String title = request.getParameter("txtTitle");
		Float price = Float.parseFloat(request.getParameter("txtPrice"));
		String type = request.getParameter("txtType");
		String description = request.getParameter("txtDescription");
		try {
			Announcement ann = new Announcement(title, price, type, description, idCustomer);
			service.addAnnouncement(ann);
			List<Announcement>list=service.getAllAnnoucementByIdCustomer(idCustomer);
			request.getSession().setAttribute("listAnnoncementConnectedCustomer", list);
			request.getRequestDispatcher("personalspace").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	
	
	
	
	
	
	
}
