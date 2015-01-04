package com.szeljic.pharmacy.Beans;

public class OrderBean {
	
	private int id, user, drug, sum, ordered;
	private boolean status, approval;
	private UserBean userRef = null;
	private DrugBean drugRef = null;
	
	public OrderBean(){
		
	}

	public UserBean getUserRef() {
		return userRef;
	}

	public void setUserRef(UserBean userRef) {
		this.userRef = userRef;
	}

	public DrugBean getDrugRef() {
		return drugRef;
	}

	public void setDrugRef(DrugBean drugRef) {
		this.drugRef = drugRef;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getDrug() {
		return drug;
	}

	public void setDrug(int drug) {
		this.drug = drug;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getOrdered() {
		return ordered;
	}

	public void setOrdered(int ordered) {
		this.ordered = ordered;
	}	

}
