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

@WebServlet("/EditPharmacistServlet")
public class EditPharmacistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditPharmacistServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		if(session.getAttribute("username") == null || (Integer)session.getAttribute("type") != 0){
			response.sendRedirect("logout.jsp");
		} else {
		
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String umcn = request.getParameter("umcn");		
			int id = Integer.parseInt(request.getParameter("id").trim());
			
			if(firstname.matches(".*\\d.*")){
				response.sendRedirect("add_pharmacist.jsp?firstname=fail&id=" + id);
			} else if(lastname.matches(".*\\d.*")){
				response.sendRedirect("add_pharmacist.jsp?lastname=fail&id=" + id);
			} else if(username.matches(".*\\d.*")){
				response.sendRedirect("add_pharmacist.jsp?username=fail&id=" + id);
			}else if(password.length() < 6){
				response.sendRedirect("add_pharmacist.jsp?password=fail&id=" + id);
			}else if(umcn.length() != 13 || umcn.matches("^[0-9]{11}$")){
				response.sendRedirect("add_pharmacist.jsp?umcn=fail&id=" + id);
			} else {
			
				UserBean user = new UserBean();
				user.setId(id);
				user.setUsername(username);
				user.setPassword(password);
				user.setFirstName(firstname);
				user.setLastName(lastname);
				user.setUmcn(umcn);
				user.setType(1);
				
				UserDAO userDAO = new UserDAOImpl();
				userDAO.editUser(user);
				
				response.sendRedirect("list_of_pharmacists.jsp");
		}
		}
	}

}
