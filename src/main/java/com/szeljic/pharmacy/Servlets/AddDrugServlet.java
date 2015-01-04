package com.szeljic.pharmacy.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.szeljic.pharmacy.Beans.DrugBean;
import com.szeljic.pharmacy.DAO.DrugDAO;
import com.szeljic.pharmacy.DAO.DrugDAOImpl;

@WebServlet("/AddDrugServlet")
public class AddDrugServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddDrugServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		if(session.getAttribute("username") == null || (Integer)session.getAttribute("type") != 1){
			response.sendRedirect("panel.jsp");
		} else {
		
			String name = request.getParameter("name");
			String producer = request.getParameter("producer");
			String uses = request.getParameter("uses");
			String howToUse = request.getParameter("how-to-use");
			String sideEffects = request.getParameter("side-effects");
			int price = Integer.parseInt(request.getParameter("price"));
			int store = Integer.parseInt(request.getParameter("store"));
			
			DrugBean drug = new DrugBean();
			drug.setName(name);
			drug.setProducer(producer);
			drug.setUses(uses);
			drug.setHowToUse(howToUse);
			drug.setSideEffects(sideEffects);
			drug.setPrice(price);
			drug.setStore(store);
			
			DrugDAO drugDAO = new DrugDAOImpl();
			drugDAO.addDrug(drug);
			
			response.sendRedirect("list_of_drugs.jsp");
		}
	}

}
