package com.szeljic.pharmacy.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchDrugServlet")
public class SearchDrugServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchDrugServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("search_for_drugs.jsp?input=" + request.getParameter("input"));
		
	}

}
