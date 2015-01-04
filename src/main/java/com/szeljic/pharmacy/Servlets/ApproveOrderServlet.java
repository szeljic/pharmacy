
package com.szeljic.pharmacy.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.szeljic.pharmacy.Beans.OrderBean;
import com.szeljic.pharmacy.DAO.OrderDAO;
import com.szeljic.pharmacy.DAO.OrderDAOImpl;

@WebServlet("/ApproveOrderServlet")
public class ApproveOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ApproveOrderServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if(session.getAttribute("username") == null || (Integer)session.getAttribute("type") != 1){
			response.sendRedirect("panel.jsp");
		} else {
			int id = Integer.parseInt(request.getParameter("id"));
			OrderDAO orderDAO = new OrderDAOImpl();
			OrderBean order = orderDAO.getOrder(id);
			order.setApproval(true);
			order.setStatus(true);
			orderDAO.editOrder(order);
			if(request.getParameter("page").equals("all")){
				response.sendRedirect("all_ordered_drugs.jsp");
			} else{
				response.sendRedirect("unresponse_orders.jsp");
			}
		}
	}

}
