package com.excilys.formation.cdb.model;

import java.util.ArrayList;


/**
 * List of Companies
 * @author Gregori Tirsatine
 */
public final class CompaniesList {

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
