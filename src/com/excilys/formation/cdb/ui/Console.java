package com.excilys.formation.cdb.ui;

import java.util.ArrayList;

import com.excilys.formation.cdb.bean.CompaniesList;
import com.excilys.formation.cdb.bean.Computer;
import com.excilys.formation.cdb.bean.ComputersList;
import com.excilys.formation.cdb.dao.CompanyDAO;
import com.excilys.formation.cdb.dao.ComputerDAO;
import com.excilys.formation.cdb.dao.ConnectionDAO;

public class Console {

	public static void main(String[] args) {

		System.out.println("Helloooo");
		// System.out.println("Affichage des ordi ?");
		// ComputerDAO computerDAO = new
		// ComputerDAO(ConnectionDAO.getConnection());
		// ComputersList comps = computerDAO.findAll();
		// System.out.println(comps);

		System.out.println("Affichage des entreprises ?");
		CompanyDAO compDAO = new CompanyDAO(ConnectionDAO.getConnection());
		CompaniesList comps = compDAO.findAll();
		System.out.println(comps);

	}

}
