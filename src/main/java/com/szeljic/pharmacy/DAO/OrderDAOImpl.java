package com.szeljic.pharmacy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.szeljic.pharmacy.Beans.DrugBean;
import com.szeljic.pharmacy.Beans.OrderBean;
import com.szeljic.pharmacy.Beans.UserBean;
import com.szeljic.pharmacy.DBConnections.ConnectionManager;

public class OrderDAOImpl implements OrderDAO{
	
	private static Connection conn = null;
	private static ResultSet rs = null;
	
	public void addOrder(OrderBean order){
		
		PreparedStatement pstmt = null;
		String query = "insert into orders(ordered, sum, drug, user, status, approval) values (?, ?, ?, ?, ?, ?)";
		
		try{
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, order.getOrdered());
			pstmt.setInt(2, order.getSum());
			pstmt.setInt(3, order.getDrug());
			pstmt.setInt(4, order.getUser());
			pstmt.setBoolean(5, false);
			pstmt.setBoolean(6, false);
			pstmt.executeUpdate();
		} catch(SQLException e){
			System.out.println("Wrong query!");
			e.printStackTrace();
		}
		finally {
			if (rs != null) {
				try {
	               rs.close();
	            } catch (Exception e) {}
	               rs = null;
	            }
		
	         if (pstmt != null) {
	            try {
	               pstmt.close();
	            } catch (Exception e) {}
	               pstmt = null;
	            }
		
	         if (conn != null) {
	            try {
	               conn.close();
	            } catch (Exception e) {
	            }
	            conn = null;
	         }
	    }
	}
	
	public List<OrderBean> getOrdersForUser(int id){
		
		List<OrderBean> list = new ArrayList<OrderBean>();
		
		Statement stmt = null;
		String query = "select * from orders where user=" + id;
	
		try{
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			OrderBean order = null;
			while(rs.next()){
				order = new OrderBean();
				order.setId(rs.getInt("id"));
				order.setOrdered(rs.getInt("ordered"));
				order.setSum(rs.getInt("sum"));
				order.setDrug(rs.getInt("drug"));
				order.setUser(rs.getInt("user"));
				order.setStatus(rs.getBoolean("status"));
				order.setApproval(rs.getBoolean("approval"));
				list.add(order);
			}	
		} catch(SQLException e){
			System.out.println("Wrong query!");
			e.printStackTrace();
		}
		finally {
			if (rs != null) {
				try {
	               rs.close();
	            } catch (Exception e) {}
	               rs = null;
	            }
		
	         if (stmt != null) {
	            try {
	               stmt.close();
	            } catch (Exception e) {}
	               stmt = null;
	            }
		
	         if (conn != null) {
	            try {
	               conn.close();
	            } catch (Exception e) {
	            }

	            conn = null;
	         }
	      }
		return list;
	}
	
	public void deleteOrder(int id){
	
		Statement stmt = null;
		String query = "delete from orders where id=" + id;
		
		try{
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch(SQLException e){
			System.out.println("Wrong query!");
			e.printStackTrace();
		}
		finally {
			if (rs != null) {
				try {
	               rs.close();
	            } catch (Exception e) {}
	               rs = null;
	            }
		
	         if (stmt != null) {
	            try {
	               stmt.close();
	            } catch (Exception e) {}
	               stmt = null;
	            }
		
	         if (conn != null) {
	            try {
	               conn.close();
	            } catch (Exception e) {
	            }
	
	            conn = null;
	         }
	    }
	}

	public List<OrderBean> getAllOrders(){
		
		Statement stmt = null;
		String query = "SELECT * FROM orders AS o INNER JOIN user AS u ON u.id = o.user INNER JOIN drug AS d ON d.id = o.drug GROUP BY o.id";
		List<OrderBean> list = new ArrayList<OrderBean>();
		OrderBean orderBean = null;
		DrugBean drugBean = null;
		UserBean userBean = null;
		
		try{
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()){
				
				orderBean = new OrderBean();
				drugBean = new DrugBean();
				userBean = new UserBean();
				
				orderBean.setId(rs.getInt("o.id"));
				orderBean.setDrug(rs.getInt("o.drug"));
				orderBean.setOrdered(rs.getInt("ordered"));
				orderBean.setSum(rs.getInt("sum"));
				orderBean.setApproval(rs.getBoolean("approval"));
				orderBean.setStatus(rs.getBoolean("status"));
				orderBean.setUser(rs.getInt("user"));
				
				drugBean.setId(rs.getInt("d.id"));
				drugBean.setName(rs.getString("d.name"));
				drugBean.setProducer(rs.getString("d.producer"));
				drugBean.setUses(rs.getString("d.uses"));
				drugBean.setHowToUse(rs.getString("d.how_to_use"));
				drugBean.setSideEffects(rs.getString("d.side_effects"));
				drugBean.setPrice(rs.getInt("price"));
				drugBean.setStore(rs.getInt("store"));
				
				userBean.setId(rs.getInt("o.user"));
				userBean.setFirstName(rs.getString("u.firstname"));
				userBean.setLastName(rs.getString("u.lastname"));
				userBean.setUsername(rs.getString("u.username"));
				userBean.setType(rs.getInt("u.type"));
				userBean.setPassword(rs.getString("u.password"));
				userBean.setUmcn(rs.getString("u.umcn"));
				
				orderBean.setDrugRef(drugBean);
				orderBean.setUserRef(userBean);
				
				list.add(orderBean);
			}
		} catch(SQLException e){
			System.out.println("Wrong query!");
			e.printStackTrace();
		}
		finally {
			if (rs != null) {
				try {
	               rs.close();
	            } catch (Exception e) {}
	               rs = null;
	            }
		
	         if (stmt != null) {
	            try {
	               stmt.close();
	            } catch (Exception e) {}
	               stmt = null;
	            }
		
	         if (conn != null) {
	            try {
	               conn.close();
	            } catch (Exception e) {
	            }
	            conn = null;
	         }
	      }
		return list;
	}
	
	public OrderBean getOrder(int id){
		
		Statement stmt = null;
		String query = "select * from orders where id=" + id;
		OrderBean order = null;
		
		try{
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()){
				order = new OrderBean();
				order.setId(rs.getInt("id"));
				order.setUser(rs.getInt("user"));
				order.setDrug(rs.getInt("drug"));
				order.setSum(rs.getInt("sum"));
				order.setOrdered(rs.getInt("ordered"));
				order.setStatus(rs.getBoolean("status"));
				order.setApproval(rs.getBoolean("approval"));
			}	
		} catch(SQLException e){
			System.out.println("Wrong query!");
			e.printStackTrace();
		}
		finally {
			if (rs != null) {
				try {
	               rs.close();
	            } catch (Exception e) {}
	               rs = null;
	            }
		
	         if (stmt != null) {
	            try {
	               stmt.close();
	            } catch (Exception e) {}
	               stmt = null;
	            }
		
	         if (conn != null) {
	            try {
	               conn.close();
	            } catch (Exception e) {
	            }
	            conn = null;
	         }
	      }
		return order;
	}

	public void editOrder(OrderBean order){
		PreparedStatement pstmt = null;
		String query = "update orders set ordered=?, sum=?, drug=?, user=?, status=?, approval=? where id=" + order.getId();
		
		try{
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, order.getOrdered());
			pstmt.setInt(2, order.getSum());
			pstmt.setInt(3, order.getDrug());
			pstmt.setInt(4, order.getUser());
			pstmt.setBoolean(5, order.isStatus());
			pstmt.setBoolean(6, order.isApproval());
			pstmt.executeUpdate();
		} catch(SQLException e){
			System.out.println("Wrong query!");
			e.printStackTrace();
		}
		finally {
			if (rs != null) {
				try {
	               rs.close();
	            } catch (Exception e) {}
	               rs = null;
	            }
		
	         if (pstmt != null) {
	            try {
	               pstmt.close();
	            } catch (Exception e) {}
	               pstmt = null;
	            }
		
	         if (conn != null) {
	            try {
	               conn.close();
	            } catch (Exception e) {
	            }
	            conn = null;
	         }
	      }
		
	}

	public List<OrderBean> searchForUsernameDrug(String username, String drug) {
		
		Statement stmt = null;
		String query = "SELECT * FROM orders AS o INNER JOIN user AS u ON u.id = o.user INNER JOIN drug AS d ON d.id = o.drug WHERE u.username='" + username + "' and d.name like '%" + drug + "%' GROUP BY o.id";
		List<OrderBean> list = new ArrayList<OrderBean>();
		OrderBean orderBean = null;
		DrugBean drugBean = null;
		UserBean userBean = null;
		
		try{
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()){
				
				orderBean = new OrderBean();
				drugBean = new DrugBean();
				userBean = new UserBean();
				
				orderBean.setId(rs.getInt("o.id"));
				orderBean.setDrug(rs.getInt("o.drug"));
				orderBean.setOrdered(rs.getInt("ordered"));
				orderBean.setSum(rs.getInt("sum"));
				orderBean.setApproval(rs.getBoolean("approval"));
				orderBean.setStatus(rs.getBoolean("status"));
				orderBean.setUser(rs.getInt("user"));
				
				drugBean.setId(rs.getInt("d.id"));
				drugBean.setName(rs.getString("d.name"));
				drugBean.setProducer(rs.getString("d.producer"));
				drugBean.setUses(rs.getString("d.uses"));
				drugBean.setHowToUse(rs.getString("d.how_to_use"));
				drugBean.setSideEffects(rs.getString("d.side_effects"));
				drugBean.setPrice(rs.getInt("price"));
				drugBean.setStore(rs.getInt("store"));
				
				userBean.setId(rs.getInt("o.user"));
				userBean.setFirstName(rs.getString("u.firstname"));
				userBean.setLastName(rs.getString("u.lastname"));
				userBean.setUsername(rs.getString("u.username"));
				userBean.setType(rs.getInt("u.type"));
				userBean.setPassword(rs.getString("u.password"));
				userBean.setUmcn(rs.getString("u.umcn"));
				
				orderBean.setDrugRef(drugBean);
				orderBean.setUserRef(userBean);
				
				list.add(orderBean);
			}
		} catch(SQLException e){
			System.out.println("Wrong query!");
			e.printStackTrace();
		}
		finally {
			if (rs != null) {
				try {
	               rs.close();
	            } catch (Exception e) {}
	               rs = null;
	            }
		
	         if (stmt != null) {
	            try {
	               stmt.close();
	            } catch (Exception e) {}
	               stmt = null;
	            }
		
	         if (conn != null) {
	            try {
	               conn.close();
	            } catch (Exception e) {
	            }
	            conn = null;
	         }
	      }
		return list;
	}
	
}
