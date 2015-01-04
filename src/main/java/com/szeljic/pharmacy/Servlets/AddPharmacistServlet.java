package com.szeljic.pharmacy.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.szeljic.pharmacy.Beans.UserBean;
import com.szeljic.pharmacy.DAO.UserDAO;
import com.szeljic.pharmacy.DAO.UserDAOImpl;

@WebServlet("/AddPharmacistServlet")
public class AddPharmacistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddPharmacistServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		if(session.getAttribute("username") == null || (Integer)session.getAttribute("type") != 0){
			response.sendRedirect("panel.jsp");
		} else {
		
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String umcn = request.getParameter("umcn");
			
			UserDAO userDAO = new UserDAOImpl();
			if(userDAO.isUserExist(username)){
				response.sendRedirect("add_pharmacist.jsp?username=exist");
			} else if(firstname.matches(".*\\d.*")){
				response.sendRedirect("add_pharmacist.jsp?firstname=fail");
			} else if(lastname.matches(".*\\d.*")){
				response.sendRedirect("add_pharmacist.jsp?lastname=fail");
			} else if(username.matches(".*\\d.*")){
				response.sendRedirect("add_pharmacist.jsp?username=fail");
			}else if(password.length() < 6){
				response.sendRedirect("add_pharmacist.jsp?password=fail");
			}else if(umcn.length() != 13 || umcn.matches("^[0-9]{11}$")){
				response.sendRedirect("add_pharmacist.jsp?umcn=fail");
			} else {
			
				UserBean user = new UserBean();
				user.setUsername(username);
				user.setPassword(password);
				user.setFirstName(firstname);
				user.setLastName(lastname);
				user.setUmcn(umcn);
				user.setType(1);
				
				userDAO.addUser(user);
				
				response.sendRedirect("list_of_pharmacists.jsp");
			
			}
		}
	}

}
