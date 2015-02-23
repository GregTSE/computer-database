package com.excilys.formation.cdb.model;

import java.sql.Date;


public class Computer {

	private Long id;
	private String name;
	private Date dateIntroduced;
	private Date dateDiscontinued;
	private String company;
	
	public Computer() {
		super();
	}
	
	public Computer(Long id, String name, Date dateIntroduced, Date dateDiscontinued, String company) {
		super();
		this.id = id;
		this.name = name;
		this.dateIntroduced = dateIntroduced;
		this.dateDiscontinued = dateDiscontinued;
		this.company = company;
	}
	
	public Computer(String name) {
		this(null, name, null, null, null);
	}

	public Long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = new Long(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateIntroduced() {
		return dateIntroduced;
	}

	public void setDateIntroduced(Date dateIntroduced) {
		this.dateIntroduced = dateIntroduced;
	}

	public Date getDateDiscontinued() {
		return dateDiscontinued;
	}

	public void setDateDiscontinued(Date dateDiscontinued) {
			this.dateDiscontinued = dateDiscontinued;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		String result =  name;
		result += (dateIntroduced != null) ? ", Introduit le : "+dateIntroduced : "";
		result += (dateDiscontinued != null) ? ", Retiré le : "+dateDiscontinued : "";
		result += (company != null) ? "\t(Entreprise : " + company + ")" : "";
		return result;
	}
	
	/**
	 * @return a String with 'id' and 'name' of company
	 */
	public String toBasicString() {
		return id + " : " + name;
	}
	
	
	
	
}
