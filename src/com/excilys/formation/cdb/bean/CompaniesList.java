package com.excilys.formation.cdb.bean;

import java.util.ArrayList;

public class CompaniesList {

	private ArrayList<Company> companies;

	public CompaniesList(ArrayList<Company> companies) {
		this.companies = companies;
	}

	@Override
	public String toString() {
		String result = "";
		for (Company comp : companies)
			result += comp.toString() + "\n";
		return result;
	}
}
