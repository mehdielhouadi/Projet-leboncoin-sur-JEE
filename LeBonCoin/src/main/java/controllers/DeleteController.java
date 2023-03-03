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

@WebServlet("/delete")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	LeMauvaisCoinServices service;
	
    public DeleteController() {
        super();
        service = new LeMauvaisCoinServices();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idAnnouncement =Integer.parseInt(request.getParameter("idAnnouncement"));
		try {
			service.deleteAnnouncement(idAnnouncement);
			int idCustomer = (int) request.getSession().getAttribute("idCustomer");
			List<Announcement>list=service.getAllAnnoucementByIdCustomer(idCustomer);
			request.getSession().setAttribute("listAnnoncementConnectedCustomer", list);
			request.getRequestDispatcher("personalspace").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



}
