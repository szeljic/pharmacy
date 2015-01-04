package com.szeljic.pharmacy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.szeljic.pharmacy.Beans.DrugBean;
import com.szeljic.pharmacy.DBConnections.ConnectionManager;

public class DrugDAOImpl implements DrugDAO{

	private static Connection conn = null;
	private static ResultSet rs = null;

	@Override
	public List<DrugBean> listOfDrugs() {

		List<DrugBean> list = new ArrayList<DrugBean>();
		Statement stmt = null;
		String query = "select * from drug";

		try{
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			DrugBean drug;
			while(rs.next()){
				drug = new DrugBean();
				drug.setId(rs.getInt("id"));
				drug.setName(rs.getString("name"));
				drug.setProducer(rs.getString("producer"));
				drug.setUses(rs.getString("uses"));
				drug.setHowToUse(rs.getString("how_to_use"));
				drug.setSideEffects(rs.getString("side_effects"));
				drug.setPrice(rs.getInt("price"));
				drug.setStore(rs.getInt("store"));
				list.add(drug);
			}
		}catch(SQLException e){
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

	@Override
	public DrugBean getDrug(int id){

		Statement stmt = null;
		String query = "select * from drug where id=" + id;
		DrugBean drug = null;

		try{
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()){
				drug = new DrugBean();
				drug.setId(rs.getInt("id"));
				drug.setName(rs.getString("name"));
				drug.setProducer(rs.getString("producer"));
				drug.setUses(rs.getString("uses"));
				drug.setHowToUse(rs.getString("how_to_use"));
				drug.setSideEffects(rs.getString("side_effects"));
				drug.setPrice(rs.getInt("price"));
				drug.setStore(rs.getInt("store"));
			}
		}catch(SQLException e){
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
		return drug;
	}


	@Override
	public void addDrug(DrugBean drug){

		PreparedStatement pstmt = null;
		String query = "insert into drug (name, producer, uses, how_to_use, side_effects, price, store) values(?, ?, ?, ?, ?, ?, ?)";

		try{
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, drug.getName());
			pstmt.setString(2, drug.getProducer());
			pstmt.setString(3, drug.getUses());
			pstmt.setString(4, drug.getHowToUse());
			pstmt.setString(5, drug.getSideEffects());
			pstmt.setInt(6, drug.getPrice());
			pstmt.setInt(7, drug.getStore());
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

	@Override
	public void editDrug(DrugBean drug){

		PreparedStatement pstmt = null;
		String query = "update drug set name=?, producer=?, uses=?, how_to_use=?, side_effects=?, price=?, store=? where id=" + drug.getId();

		try{
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, drug.getName());
			pstmt.setString(2, drug.getProducer());
			pstmt.setString(3, drug.getUses());
			pstmt.setString(4, drug.getHowToUse());
			pstmt.setString(5, drug.getSideEffects());
			pstmt.setInt(6, drug.getPrice());
			pstmt.setInt(7, drug.getStore());
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

	@Override
	public void deleteDrug(int id){

		Statement stmt = null;
		String query = "delete from drug where id=" + id;

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

	@Override
	public List<DrugBean> searchForDrug(String input){

		Statement stmt = null;
		String query = "select * from drug where name like '%" + input + "%'";
		List<DrugBean> list = new ArrayList<DrugBean>();
		DrugBean drug = null;

		try{
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				drug = new DrugBean();
				drug.setId(rs.getInt("id"));
				drug.setName(rs.getString("name"));
				drug.setProducer(rs.getString("producer"));
				drug.setUses(rs.getString("uses"));
				drug.setHowToUse(rs.getString("how_to_use"));
				drug.setSideEffects(rs.getString("side_effects"));
				drug.setPrice(rs.getInt("price"));
				drug.setStore(rs.getInt("store"));
				list.add(drug);
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
