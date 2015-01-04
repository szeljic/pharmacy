package com.szeljic.pharmacy.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchUsernameDrugServlet")
public class SearchUsernameDrugServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchUsernameDrugServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String drug = request.getParameter("drug");
		
		if(username.equals("") && drug.equals("")){
			response.sendRedirect("all_ordered_drugs.jsp");
		} else {
			response.sendRedirect("all_ordered_drugs.jsp?username=" + username + "&drug=" + drug);
		}
	}
}
