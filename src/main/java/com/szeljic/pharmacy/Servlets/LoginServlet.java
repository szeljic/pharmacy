package com.szeljic.pharmacy.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.szeljic.pharmacy.Beans.UserBean;
import com.szeljic.pharmacy.DAO.UserDAO;
import com.szeljic.pharmacy.DAO.UserDAOImpl;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserBean user = new UserBean();
		user.setUsername(username);
		user.setPassword(password);
		
		UserDAO userDAO = new UserDAOImpl();
		user = userDAO.login(user);
		HttpSession session = request.getSession(true);
		
		if(user.isValid()){
			
			
			session.setAttribute("username", username);
			session.setAttribute("id", user.getId());
			session.setAttribute("type", user.getType());
			response.sendRedirect("index.jsp");
			
		} else {
			response.sendRedirect("login.jsp?login=fail");
		}
	}
}
