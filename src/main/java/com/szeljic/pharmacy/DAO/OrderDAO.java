package com.szeljic.pharmacy.DAO;

import java.util.List;

import com.szeljic.pharmacy.Beans.OrderBean;

public interface OrderDAO {
	
	void addOrder(OrderBean order);
	List<OrderBean> getOrdersForUser(int id);
	void deleteOrder(int id);
	List<OrderBean> getAllOrders();
	OrderBean getOrder(int id);
	void editOrder(OrderBean order);
	List<OrderBean> searchForUsernameDrug(String username, String drug);

}
