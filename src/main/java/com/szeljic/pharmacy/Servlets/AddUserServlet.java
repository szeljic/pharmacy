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

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddUserServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		if (session.getAttribute("username") == null
				|| (Integer) session.getAttribute("type") != 1) {
			response.sendRedirect("panel.jsp");
		} else {

			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String umcn = request.getParameter("umcn");

			UserDAO userDAO = new UserDAOImpl();
			if (userDAO.isUserExist(username)) {
				response.sendRedirect("add_user.jsp?username=exist");
			} else if (firstname.matches(".*\\d.*")) {
				response.sendRedirect("add_user.jsp?firstname=fail");
			} else if (lastname.matches(".*\\d.*")) {
				response.sendRedirect("add_user.jsp?lastname=fail");
			} else if (username.matches(".*\\d.*")) {
				response.sendRedirect("add_user.jsp?username=fail");
			} else if (password.length() < 6) {
				response.sendRedirect("add_user.jsp?password=fail");
			} else if (umcn.length() != 13 || umcn.matches("^[0-9]{11}$")) {
				response.sendRedirect("add_user.jsp?umcn=fail");
			} else {

				UserBean user = new UserBean();
				user.setUsername(username);
				user.setPassword(password);
				user.setFirstName(firstname);
				user.setLastName(lastname);
				user.setUmcn(umcn);
				user.setType(2);

				userDAO.addUser(user);

				response.sendRedirect("list_of_users.jsp");
			}

		}
	}

}
