package com.szeljic.pharmacy.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.szeljic.pharmacy.DAO.UserDAO;
import com.szeljic.pharmacy.DAO.UserDAOImpl;

@WebServlet("/RemoveUserServlet")
public class RemoveUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public RemoveUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if(session.getAttribute("username") == null || (Integer)session.getAttribute("type") != 1){
			response.sendRedirect("panel.jsp");
		} else {
		
			int id = Integer.parseInt(request.getParameter("id"));
			
			UserDAO userDAO = new UserDAOImpl();
			userDAO.deleteUser(id);
			
			response.sendRedirect("list_of_users.jsp");
		}
	}

}
