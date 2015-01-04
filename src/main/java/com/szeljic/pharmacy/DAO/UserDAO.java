package com.szeljic.pharmacy.DAO;

import java.util.List;

import com.szeljic.pharmacy.Beans.UserBean;

public interface UserDAO {
	
	UserBean login(UserBean bean);
	List<UserBean> listOfUsers(int type);
	void addUser(UserBean user);
	UserBean getUser(int id);
	void editUser(UserBean user);
	void deleteUser(int id);
	boolean isUserExist(String username);

}
