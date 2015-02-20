package com.excilys.formation.cdb.bean;

import java.util.ArrayList;

public class ComputersList {
	
	private ArrayList<Computer> computers;

	public ComputersList(ArrayList<Computer> computers) {
		this.computers = computers;
	}

	@Override
	public String toString() {
		String result = "";
		for (Computer comp : computers){
			result += comp.toBasicString() + "\n";
		}
		return result;
	}
}
