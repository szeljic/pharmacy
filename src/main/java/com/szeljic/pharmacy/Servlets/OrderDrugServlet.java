package com.szeljic.pharmacy.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.szeljic.pharmacy.Beans.DrugBean;
import com.szeljic.pharmacy.Beans.OrderBean;
import com.szeljic.pharmacy.DAO.DrugDAO;
import com.szeljic.pharmacy.DAO.DrugDAOImpl;
import com.szeljic.pharmacy.DAO.OrderDAO;
import com.szeljic.pharmacy.DAO.OrderDAOImpl;

@WebServlet("/OrderDrugServlet")
public class OrderDrugServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public OrderDrugServlet()
	{
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int id = Integer.parseInt(request.getParameter("id"));
		int ord = Integer.parseInt(request.getParameter("order"));
		DrugDAO drugDAO = new DrugDAOImpl();
		DrugBean drug = drugDAO.getDrug(id);

		HttpSession session = request.getSession(true);

		if (ord > drug.getStore())
		{
			response.sendRedirect("order_drug.jsp?emptystore=fail");
		} else if (ord > drug.getStore())
		{
			response.sendRedirect("order_drug.jsp?notenough=fail");
		} else
		{
			drug.setStore(drug.getStore() - ord);

			drugDAO.editDrug(drug);

			OrderBean order = new OrderBean();
			order.setOrdered(Integer.parseInt(request.getParameter("order")));
			order.setSum(ord * drug.getPrice());
			order.setDrug(drug.getId());
			order.setUser((int) session.getAttribute("id"));

			OrderDAO orderDAO = new OrderDAOImpl();
			orderDAO.addOrder(order);

			response.sendRedirect("user_orders.jsp");
		}
	}
}
