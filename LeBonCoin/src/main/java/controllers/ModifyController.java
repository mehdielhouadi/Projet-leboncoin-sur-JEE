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

@WebServlet("/modify")
public class ModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LeMauvaisCoinServices service;

    public ModifyController() {
        super();
        service = new LeMauvaisCoinServices();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idAnnouncement = Integer.parseInt(request.getParameter("idAnnouncement"));
		request.getSession().setAttribute("idAnnouncement", idAnnouncement);
		request.getRequestDispatcher("modifyView.jsp").forward(request, response);;
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String title = request.getParameter("txtTitle");
			int price = Integer.parseInt(request.getParameter("txtPrice"));
			String type = request.getParameter("txtType");
			String description = request.getParameter("txtDescription");
			int idCustomer = (int) request.getSession().getAttribute("idCustomer");
			
			Announcement ann = new Announcement((int)request.getSession().getAttribute("idAnnouncement"),title,price,type,description,idCustomer);
			System.out.println(ann.getId());
			service.updateAnnouncement(ann);
			List<Announcement>list=service.getAllAnnoucementByIdCustomer(idCustomer);
			request.getSession().setAttribute("listAnnoncementConnectedCustomer", list);
			request.getRequestDispatcher("personalspace").forward(request, response);	
		} catch(SQLException e) {
			request.getRequestDispatcher("modifyView.jsp").forward(request, response);
		}
		
	}

}
