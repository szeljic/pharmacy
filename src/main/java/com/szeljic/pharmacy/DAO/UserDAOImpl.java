package com.szeljic.pharmacy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.szeljic.pharmacy.Beans.UserBean;
import com.szeljic.pharmacy.DBConnections.ConnectionManager;

public class UserDAOImpl implements UserDAO {

	private static Connection conn = null;
	private static ResultSet rs = null;

	@Override
	public UserBean login(UserBean bean) {

		String username = bean.getUsername();
		String password = bean.getPassword();

		Statement stmt = null;
		String query = "select * from user where username='" + username
				+ "' and password='" + password + "'";

		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			if (rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setFirstName(rs.getString("firstname"));
				bean.setLastName(rs.getString("lastname"));
				bean.setType(rs.getInt("type"));
				bean.setUmcn(rs.getString("umcn"));
				bean.setValid(true);
			} else {
				bean.setValid(false);
			}
		} catch (SQLException e) {
			System.out.println("Wrong query!");
			e.printStackTrace();
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
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
		return bean;
	}

	@Override
	public List<UserBean> listOfUsers(int type) {

		Statement stmt = null;
		String query = "select * from user where type=" + type;
		List<UserBean> list = new ArrayList<UserBean>();

		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			UserBean user = null;
			while (rs.next()) {
				user = new UserBean();
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setUsername(rs.getString("username"));
				user.setUmcn(rs.getString("umcn"));
				list.add(user);
			}
		} catch (SQLException e) {
			System.out.println("Wrong query!");
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
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
	public void addUser(UserBean user) {

		PreparedStatement pstmt = null;
		String query = "insert into user (firstname, lastname, username, password, umcn, type) values(?, ?, ?, ? , ?, ?)";

		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getUsername());
			pstmt.setString(4, user.getPassword());
			pstmt.setString(5, user.getUmcn());
			pstmt.setInt(6, user.getType());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Wrong query!");
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
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
	public UserBean getUser(int id) {

		Statement stmt = null;
		String query = "select * from user where id=" + id;
		UserBean user = new UserBean();

		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setUsername(rs.getString("username"));
				user.setUmcn(rs.getString("umcn"));
				user.setType(rs.getInt("type"));
			}
		} catch (SQLException e) {
			System.out.println("Wrong query!");
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
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
		return user;
	}

	@Override
	public void editUser(UserBean user) {

		PreparedStatement pstmt = null;
		String query = "update user set firstname=?, lastname=?, username=?, password=?, umcn=? where id="
				+ user.getId();

		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getUsername());
			pstmt.setString(4, user.getPassword());
			pstmt.setString(5, user.getUmcn());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Wrong query!");
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
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
	public void deleteUser(int id) {

		Statement stmt = null;
		String query = "delete from user where id=" + id;

		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Wrong query!");
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
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
	public boolean isUserExist(String username) {

		Statement stmt = null;
		String query = "select * from user where username='" + username + "'";
		boolean isExist = false;

		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				isExist = true;
			}
		} catch (SQLException e) {
			System.out.println("Wrong query!");
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
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
		return isExist;
	}

}
