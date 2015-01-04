package com.szeljic.pharmacy.DAO;

import java.util.List;

import com.szeljic.pharmacy.Beans.DrugBean;

public interface DrugDAO {
	
	List<DrugBean> listOfDrugs();
	DrugBean getDrug(int id);
	void addDrug(DrugBean drug);
	void editDrug(DrugBean drug);
	void deleteDrug(int id);
	List<DrugBean> searchForDrug(String input);

}
